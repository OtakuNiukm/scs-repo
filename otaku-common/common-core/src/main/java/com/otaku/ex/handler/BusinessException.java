package com.otaku.ex.handler;

/**
 * @Author: wz296
 * @Description: 自定义业务异常类
 * @Date: Created in 2024/10/16 下午1:24
 * @FileName: BusinessException
 * @Version: 1.0
 */
public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
