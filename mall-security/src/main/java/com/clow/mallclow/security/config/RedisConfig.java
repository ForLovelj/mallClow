package com.clow.mallclow.security.config;

import com.clow.mallclow.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Created by clow
 * Date: 2021/11/26.
 * Des: Redis配置类
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
