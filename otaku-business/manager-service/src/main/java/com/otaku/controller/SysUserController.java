package com.otaku.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.otaku.domain.SysUser;
import com.otaku.model.Result;
import com.otaku.service.SysUserService;
import com.otaku.util.AuthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 查询登录
     * @return 结果
     */
    @ApiOperation("查询登录用户信息")
    @GetMapping("info")
    public Result<SysUser> loadSysUserInfo() {
        // 获取登录用户标识
        Long loginUserId = AuthUtils.getUserId();
        // 根据用户标识查询登录用户信息
        SysUser sysUser = sysUserService.getById(loginUserId);
        return Result.success(sysUser);
    }

    /**
     * 多条件分页查询系统管理员
     * @param current 当前页
     * @param size 每页显示条目
     * @param username 用户名
     * @return 分页对象
     */
    @ApiOperation("多条件分页查询系统管理员")
    @GetMapping("page")
    @PreAuthorize("hasAuthority('sys:user:page')")
    public Result<Page<SysUser>> loadSysUserPage(@RequestParam Long current,
                                                 @RequestParam Long size,
                                                 @RequestParam(required = false) String username) {
        // 创建MyBatis分页对象
        Page<SysUser> page = new Page<>(current, size);
        //多条件分页查询
        page = sysUserService.page(page, new LambdaQueryWrapper<SysUser>()
                .like(StringUtils.hasText(username), SysUser::getUsername, username)
                .orderByDesc(SysUser::getCreateTime)
        );
        return Result.success(page);
    }

    /**
     * 新增管理员
     * @param sysUser 管理员实体
     * @return 结果
     */
    @ApiOperation("新增管理员")
    @PostMapping()
    @PreAuthorize("hasAuthority('sys:user:save')")
    public Result<String> saveSysUser(@RequestBody SysUser sysUser) {
        Integer count = sysUserService.saveSysUser(sysUser);
        return Result.handle(count > 0);
    }

    /**
     * 根据标识查询系统管理员信息
     * @param id 标识
     * @return 结果
     */
    @ApiOperation("根据标识查询系统管理员信息")
    @GetMapping("info/{id}")
    @PreAuthorize("hasAuthority('sys:user:info')")
    public Result<SysUser> loadSysUserInfo(@PathVariable Long id) {
        SysUser sysUser = sysUserService.querySysUserInfoByUserId(id);
       return Result.success(sysUser);
    }

    /**
     * 修改管理员信息
     * @param sysUser 管理员实体
     * @return 结果
     */
    @ApiOperation("修改管理员信息")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:update')")
    public Result<String> modifySysUserInfo(@RequestBody SysUser sysUser) {
        Integer count = sysUserService.modifySysUserInfo(sysUser);
        return Result.handle(count > 0);
    }

    /**
     * 批量/单个删除管理员
     * @param userIds 管理员标识集合
     * @return 结果
     */
    @ApiOperation("批量/单个删除管理员")
    @DeleteMapping("{userIds}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public Result<String> removeSysUsers(@PathVariable List<Long> userIds) {
        Boolean removed = sysUserService.removeSysUserListByUserIds(userIds);
       return Result.handle(removed);
    }
}
