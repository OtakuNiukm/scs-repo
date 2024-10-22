package com.otaku.service;

import com.otaku.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 *  @Author: wz296
 *  @Description: 
 *  @Date: Created in 2024/10/20 下午12:48 
 *  @FileName: SysUserService
 *  @Version: 1.0
 */
public interface SysUserService extends IService<SysUser>{



        Integer saveSysUser(SysUser sysUser);
    }
