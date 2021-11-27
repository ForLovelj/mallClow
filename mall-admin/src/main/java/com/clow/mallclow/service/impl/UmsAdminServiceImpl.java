package com.clow.mallclow.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.clow.mallclow.bo.AdminUserDetails;
import com.clow.mallclow.common.exception.Asserts;
import com.clow.mallclow.common.util.RequestUtil;
import com.clow.mallclow.dao.UmsAdminRoleRelationDao;
import com.clow.mallclow.dto.UmsAdminParam;
import com.clow.mallclow.mapper.UmsAdminLoginLogMapper;
import com.clow.mallclow.mapper.UmsAdminMapper;
import com.clow.mallclow.model.UmsAdmin;
import com.clow.mallclow.model.UmsAdminExample;
import com.clow.mallclow.model.UmsAdminLoginLog;
import com.clow.mallclow.model.UmsResource;
import com.clow.mallclow.security.util.JwtTokenUtil;
import com.clow.mallclow.service.UmsAdminCacheService;
import com.clow.mallclow.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by clow
 * Date: 2021/11/25.
 * Des: 后台用户管理Service实现类
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        //从redis拿缓存
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if(admin!=null) return admin;
        //构建查询条件
        final UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(username);
        final List<UmsAdmin> adminList = adminMapper.selectByExample(umsAdminExample);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            //设置redis缓存
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        final UmsAdmin umsAdmin = new UmsAdmin();
        //BeanUtils.copyProperties link:https://juejin.cn/post/6974303935972507656
        BeanUtils.copyProperties(umsAdminParam,umsAdmin);
        //设置创建日期
        umsAdmin.setCreateTime(new Date());
        //设置用户状态
        umsAdmin.setStatus(1);
        //查询是否有相同的用户名
        final UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        final List<UmsAdmin> umsAdminList = adminMapper.selectByExample(umsAdminExample);
        if (CollUtil.isNotEmpty(umsAdminList)) {
            return null;
        }
        //密码加密 避免明文存储
        final String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            final UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                Asserts.fail("密码不正确");
            }
            if(!userDetails.isEnabled()){
                Asserts.fail("帐号已被禁用");
            }
            //这个构造方法用来初始化一个已经认证的Token实例
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //根据用户信息生成token
            token = jwtTokenUtil.generateToken(userDetails);
            //插入登录日志
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if(admin==null) return;
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
        loginLogMapper.insert(loginLog);
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        List<UmsResource> resourceList = adminCacheService.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = adminRoleRelationDao.getResourceList(adminId);
        if(CollUtil.isNotEmpty(resourceList)){
            adminCacheService.setResourceList(adminId,resourceList);
        }
        return resourceList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
