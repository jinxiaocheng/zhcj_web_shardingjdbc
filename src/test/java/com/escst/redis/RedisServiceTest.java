package com.escst.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.utils.SerializeUtil;

import java.util.Set;

/**
 * @author caozx
 * @desc
 * @date 2017/9/8 14:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml",
                                    "classpath:/spring/spring-mvc.xml",
                                    "classpath:/spring/spring-redis.xml" })
public class RedisServiceTest {

	 @Autowired
	 @Qualifier("jedisTemplate")
	 private RedisTemplate redisTemplate;

    @Test
    public void test() {
    	Set<String> keys = redisTemplate.keys("com.escst.team.service.TeamService.getById(4a52d9391db3414b90cb9fbcca49cf7c*");
    	redisTemplate.delete(keys);
    }
    
    @Test
    public void test1() {
    	String key = "com.escst.team.service.TeamService.getById(4a52d9391db3414b90cb9fbcca49cf7c)";
    	byte[] value = (byte[]) redisTemplate.opsForValue().get(key);
		//反序列化从缓存中拿到的数据
        Object result = SerializeUtil.unSerialize(value);
    	System.out.println(JSONObject.toJSONString(result));
    }
}
