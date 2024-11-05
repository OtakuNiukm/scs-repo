package com.otaku.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otaku.domain.Prod;
import com.otaku.mapper.ProdMapper;
import com.otaku.service.ProdService;
/**
 *  @Author: wz296
 *  @Description: 
 *  @Date: Created in 2024/11/5 下午2:58 
 *  @FileName: ProdServiceImpl
 *  @Version: 1.0
 */
@Service
public class ProdServiceImpl extends ServiceImpl<ProdMapper, Prod> implements ProdService{

}
