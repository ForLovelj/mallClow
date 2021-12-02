package com.clow.mallclow.service;

import com.clow.mallclow.dto.UmsAdminParam;
import com.clow.mallclow.model.UmsAdmin;
import com.clow.mallclow.model.UmsResource;
import com.clow.mallclow.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by clow
 * Date: 2021/11/25.
 * Des: 后台用户管理Service
 */
public interface UmsAdminService {

    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    UmsAdmin getItem(Long id);

    /**
     * 获取用户对应角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

}
