package com.otaku.controller;

import com.otaku.domain.SysMenu;
import com.otaku.model.Result;
import com.otaku.model.SecurityUser;
import com.otaku.service.SysMenuService;
import com.otaku.util.AuthUtils;
import com.otaku.vo.MenuAndAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @Author: wz296
 * @Description: 系统权限控制层
 * @Date: Created in 2024/10/20 下午1:20
 * @FileName: SysMenuController
 * @Version: 1.0
 */
@Api(tags = "系统权限接口管理")
@RequestMapping("sys/menu")
@RestController
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    // sys/menu/nav
    @ApiOperation("查询用户的菜单权限和操作权限")
    @GetMapping("nav")
    public Result<MenuAndAuth> loadUserMenuAndAuth() {
        // 获取当前登录用户权限标识
        Long loginUserId = AuthUtils.getUserId();
        // 根据用户标识查询操作权限集合
        Set<String> loginUserPermissions = AuthUtils.getLoginUserPermissions();
        // 根据用户标识查询菜单权限集合
        Set<SysMenu> sysMenuList = sysMenuService.queryUserMenuListByUserId(loginUserId);
        // 创建菜单和操作权限对象
        MenuAndAuth menuAndAuth = new MenuAndAuth(sysMenuList, loginUserPermissions);
        return Result.success(menuAndAuth);
    }
}
