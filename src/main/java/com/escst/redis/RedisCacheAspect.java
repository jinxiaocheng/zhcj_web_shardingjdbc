package com.escst.redis;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.utils.SerializeUtil;
import com.escst.redis.annotation.RedisCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author caozx
 * @desc Redis缓存切面处理
 * @date 2017/9/21 11:23
 */
@Aspect
@Component
public class RedisCacheAspect {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheAspect.class);

    /**
     * 分隔符
     **/
    private static final String DELIMITER = ".";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param pjp
     * @return
     * @desc 获取或添加缓存
     * @author caozx
     * @date 2017/9/21 13:45
     */
    @Around(value = "@annotation(redisCache)")
    public Object redisCache(ProceedingJoinPoint pjp,RedisCache redisCache) throws Throwable {
        Object result = null;

        // 根据类名、方法名和参数生成Key
        String key = getCacheKey(pjp);

        if (logger.isDebugEnabled()) {
            logger.debug("生成key=" + key);
        }

        //检查redis是否有缓存
        byte[] value = (byte[]) redisTemplate.opsForValue().get(key);

        if (value == null) {

            //缓存未命中
            if (logger.isDebugEnabled()) {
                logger.debug("缓存未命中");
            }

            //去数据库查询
            result = pjp.proceed(pjp.getArgs());

            //把序列化结果放入缓存
            byte[] data = SerializeUtil.serialize(result);

//            RedisCache redisCache = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(RedisCache.class);

            //如果设置过期时间小于等于0，则无限期缓存，否则设置缓存时间
            if (redisCache.expire() <= 0) {
                redisTemplate.opsForValue().set(key, data);
            } else {
                redisTemplate.opsForValue().set(key, data, redisCache.expire(), redisCache.timeUnit());
            }
        } else {
            //缓存命中
            //反序列化从缓存中拿到的数据
            result = SerializeUtil.unSerialize(value);
            if (logger.isDebugEnabled()) {
                logger.debug("缓存命中,key=" + key + ";value=" + JSONObject.toJSONString(result));
            }
        }

        return result;
    }

    /**
     * @param pjp
     * @return
     * @desc 删除缓存
     * @author caozx
     * @date 2017/9/21 14:02
     */
    @Around("@annotation(com.escst.redis.annotation.RedisEvict)")
    public Object redisEvict(ProceedingJoinPoint pjp) throws Throwable {

        //得到被代理的类
        Class targetClazz = pjp.getTarget().getClass();

        //获取目录对象缓存的方法名称+参数值
        String key = getCacheKey(pjp);

        if (logger.isDebugEnabled()) {
            logger.debug("清空缓存:" + key);
        }
        redisTemplate.delete(key);
        return pjp.proceed(pjp.getArgs());
    }

    /**
     * 获取被拦截方法对象
     * MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
     * 而缓存的注解在实现类的方法上
     * 所以应该使用反射获取当前对象的方法对象
     */
    private Method getMethod(ProceedingJoinPoint pjp) {
        //获取参数的类型
        Class[] argTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;

    }

    /**
     * @param pjp
     * @return key格式：全类名.方法名.参数类型
     * @desc 根据类名、方法名和参数生成Key
     * @author caozx
     * @date 2017/9/21 16:04
     */
    private String getCacheKey(ProceedingJoinPoint pjp) {
        String clazzName = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        StringBuilder key = new StringBuilder();
        key.append(clazzName);
        key.append(DELIMITER);
        key.append(methodName);

        if (args != null && args.length > 0) {
            key.append("(");
            for (int i = 0; i < args.length; i++) {
                String argStr = args[i] == null ? "" : args[i].toString();
                if (i == args.length - 1) {
                    key.append(argStr);
                } else {
                    key.append(argStr);
                    key.append(",");
                }
            }
            key.append(")");
        }
        return key.toString();
    }

}
