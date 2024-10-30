package com.otaku.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otaku.constant.ManagerConstants;
import com.otaku.domain.SysMenu;
import com.otaku.mapper.SysMenuMapper;
import com.otaku.service.SysMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: wz296
 * @Description:
 * @Date: Created in 2024/10/20 下午12:48
 * @FileName: SysMenuServiceImpl
 * @Version: 1.0
 */
@Service
@CacheConfig(cacheNames = "com.otaku.service.impl.SysMenuServiceImpl")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    @Cacheable(key = "#loginUserId")
    public Set<SysMenu> queryUserMenuListByUserId(Long loginUserId) {
        // 查询用户的菜单权限
        Set<SysMenu> sysMenus = sysMenuMapper.selectUserMenuListByUserId(loginUserId);
        // 将菜单权限集合的数据转换为树结构（即：数据结构应该有层级关系）
        return transformTree(sysMenus, 0L);
    }

    /**
     * 将菜单权限集合转换为树结构
     * 1 已知菜单深度 <= 2
     * 2 未知菜单深度
     *
     * @param sysMenus
     * @param pid
     * @return
     */
    private Set<SysMenu> transformTree(Set<SysMenu> sysMenus, Long pid) {
        // 已知菜单深度 <= 2
        // 从菜单集合中获取根节点集合
//        Set<SysMenu> rootMenus = sysMenus.stream()
//                .filter(item -> item.getParentId().equals(pid))
//                .collect(Collectors.toSet());
//        // 遍历根节点集合，为每个根节点添加子节点集合
//        rootMenus.forEach(rootMenu -> {
//            // 从菜单集合中过滤出它的父节点与当前根节点值一致的菜单集合
//            Set<SysMenu> childMenus = sysMenus.stream()
//                    .filter(item -> item.getParentId().equals(rootMenu.getMenuId()))
//                    .collect(Collectors.toSet());
//            rootMenu.setList(childMenus);
//        });

        // 未知菜单深度
        // 获取根节点集合
        Set<SysMenu> rootMenus = sysMenus.stream()
                .filter(m -> m.getParentId().equals(pid))
                .collect(Collectors.toSet());
        // 循环节点集合
        rootMenus.forEach(rootMenu -> {
            // 递归调用
            rootMenu.setList(transformTree(sysMenus, rootMenu.getMenuId()));
        });
        return rootMenus;
    }

    /**
     * 查询所有菜单权限集合
     * @return List<SysMenu> 所有权限集合
     */
    @Override
    @Cacheable(key = ManagerConstants.SYS_ALL_MENU_KEY)
    public List<SysMenu> queryAllSysMenuList() {
        return sysMenuMapper.selectList(null);
    }

    /**
     * 新增权限
     * @param sysMenu 权限实体
     * @return Boolean
     */
    @Override
    @CacheEvict(key = ManagerConstants.SYS_ALL_MENU_KEY)
    public Boolean saveSysMenu(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu) > 0;
    }
}
