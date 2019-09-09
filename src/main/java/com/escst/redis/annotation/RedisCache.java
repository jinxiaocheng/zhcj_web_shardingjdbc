package com.escst.redis.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author caozx
 * @desc 缓存注解
 * @date 2017/9/21 11:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface RedisCache {

    /**默认缓存时间，1天**/
    long expire() default 1;

    /**默认缓存时间单位：天**/
    TimeUnit timeUnit() default TimeUnit.DAYS;
}
