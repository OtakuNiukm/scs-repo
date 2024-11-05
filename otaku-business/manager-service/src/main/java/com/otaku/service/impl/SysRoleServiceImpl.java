package com.otaku.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otaku.constant.ManagerConstants;
import com.otaku.domain.SysRole;
import com.otaku.domain.SysRoleMenu;
import com.otaku.mapper.SysRoleMapper;
import com.otaku.mapper.SysRoleMenuMapper;
import com.otaku.service.SysRoleMenuService;
import com.otaku.service.SysRoleService;
import com.otaku.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wz296
 * @Description:
 * @Date: Created in 2024/10/20 下午12:48
 * @FileName: SysRoleServiceImpl
 * @Version: 1.0
 */
@Service
@CacheConfig(cacheNames = "com.otaku.service.impl.SysRoleServiceImpl")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 查询所有角色数据（全量查询）
     * 全量查询需要将数据放到缓存中
     *
     * @return List<SysRole>
     */
    @Override
    @Cacheable(key = ManagerConstants.SYS_ALL_ROLE_KEY)
    public List<SysRole> querySysRoleList() {
        return sysRoleMapper.selectList(new LambdaQueryWrapper<SysRole>()
                .orderByDesc(SysRole::getCreateTime));
    }

    /**
     * 新增角色
     * 1. 新增角色
     * 2. 新增角色与权限关系集合
     *
     * @param sysRole 新增角色对象
     * @return Boolean
     */
    @Override
    @CacheEvict(key = ManagerConstants.SYS_ALL_ROLE_KEY)
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveSysRole(SysRole sysRole) {
        // 新增角色
        sysRole.setCreateTime(new Date());
        sysRole.setCreateUserId(AuthUtils.getLoginUser().getUserId());
        int i = sysRoleMapper.insert(sysRole);
        if (i > 0) {
            // 获取角色ID
            Long roleId = sysRole.getRoleId();
            // 新增角色与权限的关系记录
            // 获取角色对应的权限集合
            List<Long> menuIdList = sysRole.getMenuIdList();
            // 创建角色与权限集合
            List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
            // 判断是否有值
            if (CollectionUtil.isNotEmpty(menuIdList) && !menuIdList.isEmpty()) {
                // 循环添加角色与权限的关系
                menuIdList.forEach(menuId -> {
                    // 创建角色与权限关系记录
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setRoleId(roleId);
                    sysRoleMenu.setMenuId(menuId);
                    // 收集角色与权限关系记录
                    sysRoleMenuList.add(sysRoleMenu);
                });
                // 批量添加角色与权限关系集合
                sysRoleMenuService.saveBatch(sysRoleMenuList);
            }
        }
        return i > 0;
    }

    /**
     * 根据角色ID查询角色信息
     *
     * @param roleId 角色ID
     * @return SysRole
     */
    @Override
    public SysRole querySysRoleInfoById(Long roleId) {
        // 根据标识查询角色信息
        SysRole sysRole = sysRoleMapper.selectById(roleId);
        // 根据角色标识查询角色与权限关系集合
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, roleId)
        );
        // 判断是否有值
        if (CollectionUtil.isNotEmpty(sysRoleMenuList) && !sysRoleMenuList.isEmpty()) {
            // 有值
            // 获取权限标识集合
            List<Long> menuIdList = sysRoleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
            sysRole.setMenuIdList(menuIdList);
        }
        return sysRole;
    }

    /**
     * 修改角色信息
     * 1. 修改角色信息
     * 2. 修改角色与权限关系集合
     *
     * @param sysRole 修改角色对象
     * @return Boolean
     */
    @Override
    @CacheEvict(key = ManagerConstants.SYS_ALL_ROLE_KEY)
    @Transactional(rollbackFor = Exception.class)
    public Boolean modifySysRole(SysRole sysRole) {
        // 获取角色ID
        Long roleId = sysRole.getRoleId();
        // 删除角色原有的权限集合
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, roleId)
        );
        // 获取角色对应的权限集合
        List<Long> menuIdList = sysRole.getMenuIdList();
        // 创建角色与权限集合
        List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
        // 判断是否有值
        if (CollectionUtil.isNotEmpty(menuIdList) && !menuIdList.isEmpty()) {
            // 循环添加角色与权限的关系
            menuIdList.forEach(menuId -> {
                // 创建角色与权限关系记录
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setMenuId(menuId);
                // 收集角色与权限关系记录
                sysRoleMenuList.add(sysRoleMenu);
            });
            // 批量添加角色与权限关系集合
            sysRoleMenuService.saveBatch(sysRoleMenuList);
        }
        // 修改角色信息
        return sysRoleMapper.updateById(sysRole) > 0;
    }

    /**
     * 批量或单个删除角色信息
     *
     * @param roleIdList 角色ID集合
     * @return Boolean
     */
    @Override
    @CacheEvict(key = ManagerConstants.SYS_ALL_ROLE_KEY)
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeSysRoleListByIds(List<Long> roleIdList) {
        // 批量或单个删除角色与权限关系集合
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>()
                .in(SysRoleMenu::getRoleId, roleIdList)
        );
        // 批量或单个删除角色信息
        return sysRoleMapper.deleteBatchIds(roleIdList) == roleIdList.size();
    }
}
