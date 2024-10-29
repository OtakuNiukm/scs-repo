package com.otaku.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.otaku.domain.SysRole;
import com.otaku.model.Result;
import com.otaku.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询所有角色
     * @return 角色列表
     */
    @ApiOperation("查询所有角色 ")
    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('sys:role:list')")
    public Result<List<SysRole>> loadSysRoleList() {
        List<SysRole> list = sysRoleService.querySysRoleList();
        return Result.success(list);
    }

    /**
     * 多条件分页查询角色列表
     * @param current 当前页码
     * @param size 每页显示条目
     * @param roleName 角色名称
     * @return 分页对象
     */
    @ApiOperation("多条件分页查询角色列表")
    @GetMapping("page")
    @PreAuthorize("hasAuthority('sys:role:page')")
    public Result<Page<SysRole>> loadSysRolePage(@RequestParam Long current,
                                                 @RequestParam Long size,
                                                 @RequestParam(required = false) String roleName) {
        // 创建一个分页对象
        Page<SysRole> page = new Page<>(current, size);
        // 多条件分页查询角色列表
        sysRoleService.page(page, new LambdaQueryWrapper<SysRole>()
                .like(StringUtils.hasText(roleName), SysRole::getRoleName, roleName)
                .orderByDesc(SysRole::getCreateTime)
        );
        return Result.success(page);
    }

    /**
     *  新增角色
     * @param sysRole 角色对象
     * @return 新增结果
     */
    @ApiOperation("新增角色")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:role:save')")
    public Result<String> saveSysRole(@RequestBody SysRole sysRole) {
        Boolean saved = sysRoleService.saveSysRole(sysRole);
       return Result.handle(saved);
    }

    /**
     * 根据标识查询角色详情
     * @param roleId 标识
     * @return 角色详情
     */
    @ApiOperation("根据标识查询角色详情")
    @GetMapping("info/{roleId}")
    @PreAuthorize("hasAuthority('sys:role:info')")
    public Result<SysRole> loadSysRoleInfo(@PathVariable Long roleId) {
        SysRole sysRole = sysRoleService.querySysRoleInfoById(roleId);
       return Result.success(sysRole);
    }
}
