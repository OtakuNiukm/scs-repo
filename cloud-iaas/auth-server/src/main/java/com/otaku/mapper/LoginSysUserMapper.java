package com.otaku.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.otaku.domain.LoginSysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @Author: wz296
 * @Description:
 * @Date: Created in 2024/10/13 上午10:22
 * @FileName: SysUserMapper
 * @Version: 1.0
 */
@Mapper
public interface LoginSysUserMapper extends BaseMapper<LoginSysUser> {

    /**
     * 根据用户标识查询用户的权限集合
     *
     * @param userId
     * @return
     */
    Set<String> selectPermsByUserId(Long userId);

}