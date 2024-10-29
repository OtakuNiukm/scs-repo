package com.otaku.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.otaku.domain.SysRole;

import java.util.List;

/**
 * @Author: wz296
 * @Description:
 * @Date: Created in 2024/10/20 下午12:48
 * @FileName: SysRoleService
 * @Version: 1.0
 */
public interface SysRoleService extends IService<SysRole> {


    List<SysRole> querySysRoleList();
}
