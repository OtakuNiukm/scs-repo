package com.otaku.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.otaku.constant.ProductConstants;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.otaku.domain.Category;
import com.otaku.mapper.CategoryMapper;
import com.otaku.service.CategoryService;
/**
 *  @Author: wz296
 *  @Description: 
 *  @Date: Created in 2024/11/5 下午2:58 
 *  @FileName: CategoryServiceImpl
 *  @Version: 1.0
 */
@Service
@CacheConfig(cacheNames = "com.otaku.service.impl.CategoryServiceImpl")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询所有类目
     * @return 所有类目列表
     */
    @Override
    @Cacheable(key = ProductConstants.ALL_CATEGORY_LIST_KEY)
    public List<Category> queryAllCategoryList() {
        return categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .orderByDesc(Category::getSeq));
    }

    /**
     * 查询所有一级类目
     * @return 所有一级类目列表
     */
    @Override
    @Cacheable(key = ProductConstants.FIRST_CATEGORY_LIST_KEY)
    public List<Category> queryFirstCategoryList() {
        return categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .eq(Category::getParentId, 0)
                .eq(Category::getStatus, 1)
                .orderByDesc(Category::getSeq));
    }
}
