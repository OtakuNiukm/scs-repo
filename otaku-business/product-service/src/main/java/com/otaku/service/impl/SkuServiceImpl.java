package com.otaku.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otaku.mapper.SkuMapper;
import com.otaku.domain.Sku;
import com.otaku.service.SkuService;
/**
 *  @Author: wz296
 *  @Description: 
 *  @Date: Created in 2024/11/5 下午2:58 
 *  @FileName: SkuServiceImpl
 *  @Version: 1.0
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService{

}
