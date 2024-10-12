package com.otaku.constant;

/**
 * 认证授权常量类
 */
public interface AuthConstants {

    /**
     * 在请求头中放Token头前缀
     */
    String AUTHORIZATION = "Authorization";

    /**
     * Token值的前缀
     */
    String BEARER = "Bearer";

    /**
     * Token值存放在redis中的前缀
     */
    String LOGIN_TOKEN_PREFIX = "login_token:";
}
