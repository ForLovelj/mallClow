package com.clow.mallclow.demo.service;

import com.clow.mallclow.demo.dto.PmsBrandDto;
import com.clow.mallclow.model.PmsBrand;

import java.util.List;

/**
 * Created by clow
 * Date: 2021/11/25.
 * Des: DemoService接口
 */
public interface DemoService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrandDto pmsBrandDto);

    int updateBrand(Long id, PmsBrandDto pmsBrandDto);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
