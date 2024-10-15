package com.otaku.strategy.impl;

import com.otaku.constant.AuthConstants;
import com.otaku.strategy.LoginStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * 商城购物系统登录实现
 */
@Service(AuthConstants.MEMBER_LOGIN)
public class MemberLoginStrategy implements LoginStrategy {

    @Override
    public UserDetails realLogin(String username) {
        return null;
    }
}
