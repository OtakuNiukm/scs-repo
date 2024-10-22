package com.otaku.controller;

import com.otaku.domain.SysUser;
import com.otaku.model.Result;
import com.otaku.service.SysUserService;
import com.otaku.util.AuthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wz296
 * @Description: 系统管理员控制层
 * @Date: Created in 2024/10/22 下午2:55
 * @FileName: SysUserController
 * @Version: 1.0
 */
@Api(tags = "系统管理员接口管理")
@RequestMapping("sys/user")
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

   @ApiOperation("查询登录用户信息")
   @GetMapping("info")
   public Result<SysUser> loadSysUserInfo() {
       // 获取登录用户标识
       Long loginUserId = AuthUtils.getUserId();
       // 根据用户标识查询登录用户信息
       SysUser sysUser = sysUserService.getById(loginUserId);
       return Result.success(sysUser);
   }
}
