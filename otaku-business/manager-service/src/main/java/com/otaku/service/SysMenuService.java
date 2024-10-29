package com.otaku.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.otaku.domain.SysMenu;

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
     * @return
     */
    Set<SysMenu> queryUserMenuListByUserId(Long loginUserId);
}
