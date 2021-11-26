package com.clow.mallclow.security.annotation;

import java.lang.annotation.*;

/**
 * Created by clow
 * Date: 2021/11/25.
 * Des: 自定义注解，有该注解的缓存方法会抛出异常
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
