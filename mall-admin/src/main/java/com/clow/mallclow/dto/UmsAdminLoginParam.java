package com.clow.mallclow.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * Created by clow
 * Date: 2021/11/26.
 * Des: 用户登录参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam {
    @NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
