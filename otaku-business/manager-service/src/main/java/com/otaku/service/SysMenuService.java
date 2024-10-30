package com.otaku.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.otaku.domain.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * @Author: wz296
 * @Description:
 * @Date: Created in 2024/10/20 下午12:48
 * @FileName: SysMenuService
 * @Version: 1.0
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 根据用户id查询菜单列表
     *
     * @param loginUserId
     * @return Set
     */
    Set<SysMenu> queryUserMenuListByUserId(Long loginUserId);

    /**
     * 查询所有菜单列表
     * @return List
     */
    List<SysMenu> queryAllSysMenuList();

    /**
     * 新增菜单
     * @param sysMenu 权限实体
     * @return Boolean
     */
    Boolean saveSysMenu(SysMenu sysMenu);
}
