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
    String BEARER = "bearer ";

    /**
     * Token值存放在redis中的前缀
     */
    String LOGIN_TOKEN_PREFIX = "login_token:";

    /**
     * 登录URL
     */
    String LOGIN_URL = "/doLogin";

    /**
     * 登出URL
     */
    String LOGOUT_URL = "/doLogout";

    /**
     * 登录类型
     */
    String LOGIN_TYPE = "loginType";

    /**
     * 登录类型值： 商城后台管理系统用户
     */
    String SYS_USER_LOGIN = "sys_user_login";

    /**
     * 登录类型值： 商城购物系统用户
     */
    String MEMBER_LOGIN = "member_login";

    /**
     * Token有效时常(单位秒，4小时)
     */
    Long TOKEN_TIME = 14400L;

    /**
     * Token过期阈值(单位秒，1小时)
     */
    Long TOKEN_EXPIRE_THRESHOLD_TIME = 60 * 60L;
}
