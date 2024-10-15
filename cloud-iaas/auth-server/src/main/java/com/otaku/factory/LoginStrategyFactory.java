package com.otaku.factory;

import cn.hutool.core.util.StrUtil;
import com.otaku.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录策略工厂类
 */
@Component
public class LoginStrategyFactory {

    @Autowired
    private Map<String, LoginStrategy> loginStrategyMap = new HashMap<>();

    public LoginStrategy getInstance(String loginType) {
        loginType = StrUtil.toUnderlineCase(loginType);
        return loginStrategyMap.get(loginType);
    }

}
