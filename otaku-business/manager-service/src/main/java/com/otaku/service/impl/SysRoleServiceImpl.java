package com.otaku.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otaku.constant.ManagerConstants;
import com.otaku.domain.SysRole;
import com.otaku.mapper.SysRoleMapper;
import com.otaku.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private SysRoleMapper SysRoleMapper;

    /**
     * 查询所有角色数据（全量查询）
     * 全量查询需要将数据放到缓存中
     *
     * @return
     */
    @Override
    @Cacheable(key = ManagerConstants.SYS_ALL_ROLE_KEY)
    public List<SysRole> querySysRoleList() {
        return SysRoleMapper.selectList(new LambdaQueryWrapper<SysRole>()
                .orderByDesc(SysRole::getCreateTime));
    }
}
