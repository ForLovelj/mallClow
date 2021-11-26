package com.clow.mallclow.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;


/**
 * Created by clow
 * Date: 2021/11/25.
 * Des: 动态权限相关业务类
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
