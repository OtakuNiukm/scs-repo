package com.otaku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.otaku.domain.SysMenu;

import java.util.Set;

/**
 * @Author: wz296
 * @Description:
 * @Date: Created in 2024/10/20 下午12:48
 * @FileName: SysMenuMapper
 * @Version: 1.0
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户id查询菜单列表
     *
     * @param loginUserId
     * @return
     */
    Set<SysMenu> selectUserMenuListByUserId(Long loginUserId);
}