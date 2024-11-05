package com.otaku.service;

import com.otaku.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  @Author: wz296
 *  @Description: 
 *  @Date: Created in 2024/11/5 下午2:58 
 *  @FileName: CategoryService
 *  @Version: 1.0
 */
public interface CategoryService extends IService<Category>{

    /**
     * 查询所有分类
     * @return 类目列表
     */
    List<Category> queryAllCategoryList();

    /**
     * 查询一级类目
     * @return 一级类目列表
     */
    List<Category> queryFirstCategoryList();
}
