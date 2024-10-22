package com.otaku.controller;

import com.otaku.domain.SysRole;
import com.otaku.model.Result;
import com.otaku.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wz296
 * @Description: 系统角色管理控制层
 * @Date: Created in 2024/10/22 下午4:07
 * @FileName: SysRoleController
 * @Version: 1.0
 */
@Api(tags = "系统角色接口管理")
@RequestMapping("sys/role")
@RestController
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("查询所有角色 ")
    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('sys:role:list')")
    public Result<List<SysRole>> loadSysRoleList() {
        List<SysRole> list = sysRoleService.querySysRoleList();
        return Result.success(list);
    }
}
