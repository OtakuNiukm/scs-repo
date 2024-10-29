package com.otaku.strategy;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * 登录策略接口
 */
public interface LoginStrategy {

    /**
     * 登录方法
     *
     * @param username
     * @return
     */
    UserDetails realLogin(String username);

}
