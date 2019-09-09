package com.escst.redis.annotation;

import java.lang.annotation.*;

/**
 * @author caozx
 * @desc  清除过期缓存注解，放置于update delete insert 类型逻辑之上
 * @date 2017/9/21 13:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface RedisEvict {

}
