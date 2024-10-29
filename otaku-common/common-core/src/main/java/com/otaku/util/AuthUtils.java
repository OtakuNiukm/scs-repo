package com.otaku.util;

import com.otaku.model.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

/**
 * @Author: wz296
 * @Description: 认证授权工具类
 * @Date: Created in 2024/10/20 下午1:41
 * @FileName: AuthUtils
 * @Version: 1.0
 */
public class AuthUtils {


    /**
     * 获取容器中用户对象
     *
     * @return
     */
    public static SecurityUser getLoginUser() {
        // 获取当前登录用户权限标识
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前登录用户ID
     *
     * @return
     */
    public static Long getUserId() {
        return getLoginUser().getUserId();
    }

    /**
     * 获取当前登录用户权限
     *
     * @return
     */
    public static Set<String> getLoginUserPermissions() {
        return getLoginUser().getPerms();
    }
}
