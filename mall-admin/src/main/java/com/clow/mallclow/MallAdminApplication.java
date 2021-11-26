package com.clow.mallclow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by clow
 * Date: 2021/11/25.
 * Des: 后台商城管理系统
 * cms_*：内容管理模块相关表
 * oms_*：订单管理模块相关表
 * pms_*：商品模块相关表
 * sms_*：营销模块相关表
 * ums_*：会员模块相关表
 */
@SpringBootApplication
public class MallAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }
}
