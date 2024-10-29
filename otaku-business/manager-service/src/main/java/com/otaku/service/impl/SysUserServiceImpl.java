package com.otaku.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otaku.domain.SysUser;
import com.otaku.domain.SysUserRole;
import com.otaku.mapper.SysUserMapper;
import com.otaku.mapper.SysUserRoleMapper;
import com.otaku.service.SysUserRoleService;
import com.otaku.service.SysUserService;
import com.otaku.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: wz296
 * @Description:
 * @Date: Created in 2024/10/20 下午12:48
 * @FileName: SysUserServiceImpl
 * @Version: 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 新增管理员
     * 1.新增管理员
     * 2.新增管理员与角色关系
     *
     * @param sysUser
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveSysUser(SysUser sysUser) {
        // 新增管理员
        sysUser.setCreateUserId(AuthUtils.getLoginUser().getUserId());
        sysUser.setCreateTime(new Date());
        sysUser.setShopId(1L);
        int i = sysUserMapper.insert(sysUser);
        if (i > 0) {
            // 获取管理员标识
            Long userId = sysUser.getUserId();
            // 获取管理员与角色的关系
            // 获取角色id集合
            List<Long> roleIdList = sysUser.getRoleIdList();
            // 判断是否有值
            if (roleIdList != null && !roleIdList.isEmpty()) {
                // 创建关系集合
                List<SysUserRole> sysUserRoleList = new ArrayList<>();
                // 循环添加管理员与角色关系
                roleIdList.forEach(roleId -> {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(userId);
                    sysUserRole.setRoleId(roleId);
                    // 新增管理员与角色的关系 (不建议在循环中操作数据库)
//                    sysUserRoleMapper.insert(sysUserRole);
                    sysUserRoleList.add(sysUserRole);
                });
                // 批量新增
                sysUserRoleService.saveBatch(sysUserRoleList);
            }
        }
        return i;
    }
}
