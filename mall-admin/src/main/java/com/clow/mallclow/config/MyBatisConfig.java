package com.clow.mallclow.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by clow
 * Date: 2021/11/24.
 * Des: MyBatis相关配置
 */
@Configuration//用于指定当前类是一个 Spring   配置类，当创建容器时会从该类上加载注解
@EnableTransactionManagement //开启事务管理
@MapperScan({"com.clow.mallclow.mapper","com.clow.mallclow.dao"})//扫描mapper
public class MyBatisConfig {
}
