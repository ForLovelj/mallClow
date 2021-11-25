package com.clow.mallclow.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by clow
 * Date: 2021/11/24.
 * Des:
 */
@SpringBootApplication(scanBasePackages = "com.clow.mallclow")//全局异常抽取在common包导致Spring启动时找不到，指定扫描basePackages
public class MallDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallDemoApplication.class, args);
    }
}
