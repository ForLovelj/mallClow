package com.clow.mallclow.service.impl;

import com.clow.mallclow.mapper.UmsResourceMapper;
import com.clow.mallclow.model.UmsResource;
import com.clow.mallclow.model.UmsResourceExample;
import com.clow.mallclow.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by clow
 * Date: 2021/11/26.
 * Des: 后台资源管理Service实现类
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public int create(UmsResource umsResource) {
        return 0;
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        return 0;
    }

    @Override
    public UmsResource getItem(Long id) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }
}
