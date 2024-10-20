package com.otaku.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otaku.domain.SysLog;
import com.otaku.mapper.SysLogMapper;
import com.otaku.service.SysLogService;
/**
 *  @Author: wz296
 *  @Description: 
 *  @Date: Created in 2024/10/20 下午12:48 
 *  @FileName: SysLogServiceImpl
 *  @Version: 1.0
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService{

}