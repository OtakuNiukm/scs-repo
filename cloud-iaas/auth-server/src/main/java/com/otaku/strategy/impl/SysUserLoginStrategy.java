package com.otaku.strategy.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.otaku.constant.AuthConstants;
import com.otaku.domain.LoginSysUser;
import com.otaku.mapper.LoginSysUserMapper;
import com.otaku.model.SecurityUser;
import com.otaku.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 商城后台管理系统登录策略实现
 */
@Service(AuthConstants.SYS_USER_LOGIN)
public class SysUserLoginStrategy implements LoginStrategy {

    @Autowired
    private LoginSysUserMapper loginSysUserMapper;
    /**
     * 根据用户登录类型获取具体登录策略
     * @param username
     * @return
     */
    @Override
    public UserDetails realLogin(String username) {
        // 根据用户名称查询用户对象
        LoginSysUser loginSysUser = loginSysUserMapper.selectOne(
                new LambdaQueryWrapper<LoginSysUser>()
                        .eq(LoginSysUser::getUsername, username)
        );
        if (ObjectUtil.isNotNull(loginSysUser)) {
            // 根据用户标识查询用户权限集合
            Set<String> perms = loginSysUserMapper.selectPermsByUserId(loginSysUser.getUserId());
            // 创建安全用户对象SecurityUser
            SecurityUser securityUser = new SecurityUser();
            securityUser.setUserId(loginSysUser.getUserId());
            securityUser.setPassword(loginSysUser.getPassword());
            securityUser.setShopId(loginSysUser.getShopId());
            securityUser.setStatus(loginSysUser.getStatus());
            securityUser.setLoginType(AuthConstants.SYS_USER_LOGIN);
            // 判断用户是否有权限
            if (CollectionUtil.isNotEmpty(perms) && !perms.isEmpty()) {
                securityUser.setPerms(perms);
            }

            return securityUser;
        }

        return null;
    }
}
