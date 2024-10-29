package com.otaku.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.otaku.domain.SysUser;

/**
 * @Author: wz296
 * @Description:
 * @Date: Created in 2024/10/20 下午12:48
 * @FileName: SysUserService
 * @Version: 1.0
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 新增管理员
     * @param sysUser
     * @return
     */
    Integer saveSysUser(SysUser sysUser);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    SysUser querySysUserInfoByUserId(Long id);

    /**
     * 修改管理员信息
     * @param sysUser
     * @return
     */
    Integer modifySysUserInfo(SysUser sysUser);
}
