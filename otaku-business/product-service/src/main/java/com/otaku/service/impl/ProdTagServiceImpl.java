package com.otaku.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otaku.mapper.ProdTagMapper;
import com.otaku.domain.ProdTag;
import com.otaku.service.ProdTagService;
/**
 *  @Author: wz296
 *  @Description: 
 *  @Date: Created in 2024/11/5 下午2:58 
 *  @FileName: ProdTagServiceImpl
 *  @Version: 1.0
 */
@Service
public class ProdTagServiceImpl extends ServiceImpl<ProdTagMapper, ProdTag> implements ProdTagService{

}
