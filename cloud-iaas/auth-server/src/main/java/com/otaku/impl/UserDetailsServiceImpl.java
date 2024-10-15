package com.otaku.impl;

import com.otaku.constant.AuthConstants;
import com.otaku.factory.LoginStrategyFactory;
import com.otaku.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目认证流程
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private LoginStrategyFactory loginStrategyFactory;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request;
        String loginType;
        LoginStrategy instance;
        request = requestAttributes.getRequest();
        // 获取登录类型
        loginType = request.getHeader(AuthConstants.LOGIN_TYPE);
        // 判断请求来自于哪个系统
        // 使用策略设计模式
        if (!StringUtils.hasText(loginType)) {
            throw new InternalAuthenticationServiceException("非法登录，登陆类型不匹配");
        }
        instance = loginStrategyFactory.getInstance(loginType);

        return instance.realLogin(username);
    }
}
