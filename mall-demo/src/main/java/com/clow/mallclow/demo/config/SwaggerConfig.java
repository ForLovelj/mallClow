package com.clow.mallclow.demo.config;

import com.clow.mallclow.common.config.BaseSwaggerConfig;
import com.clow.mallclow.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by clow
 * Date: 2021/11/24.
 * Des: Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.clow.mallclow.demo.controller")
                .title("mall-demo系统")
                .description("SpringBoot版本中的一些示例")
                .contactName("clow")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
