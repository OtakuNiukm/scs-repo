package com.otaku.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otaku.mapper.SysUserMapper;
import com.otaku.domain.SysUser;
import com.otaku.service.SysUserService;
/**
 *  @Author: wz296
 *  @Description: 
 *  @Date: Created in 2024/10/20 下午12:48 
 *  @FileName: SysUserServiceImpl
 *  @Version: 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

}
