package com.clow.mallclow.common.api;

/**
 * Created by clow
 * Date: 2021/11/24.
 * Des: 常用API返回对象接口
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}
