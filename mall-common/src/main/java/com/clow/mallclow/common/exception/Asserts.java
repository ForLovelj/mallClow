package com.clow.mallclow.common.exception;


import com.clow.mallclow.common.api.IErrorCode;

/**
 * Created by clow
 * Date: 2021/11/24.
 * Des:  断言处理类，用于抛出各种API异常
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
