package com.otaku.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.otaku.constant.ManagerConstants;
import com.otaku.domain.SysRole;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wz296
 * @Description:
 * @Date: Created in 2024/10/20 下午12:48
 * @FileName: SysRoleService
 * @Version: 1.0
 */
public interface SysRoleService extends IService<SysRole> {


    /**
     * 查询所有角色
     * @return List<SysRole>
     */
    List<SysRole> querySysRoleList();

    /**
     * 新增角色
     * @param sysRole 新增角色对象
     * @return Boolean
     */
    Boolean saveSysRole(SysRole sysRole);

    /**
     * 根据角色ID查询角色信息
     * @param roleId 角色ID
     * @return SysRole
     */
    SysRole querySysRoleInfoById(Long roleId);

    /**
     * 修改角色
     * @param sysRole 修改角色对象
     * @return Boolean
     */
    Boolean modifySysRole(SysRole sysRole);

    /**
     * 删除角色
     * @param roleIdList 角色ID集合
     * @return Boolean
     */
    Boolean removeSysRoleListByIds(List<Long> roleIdList);
}
