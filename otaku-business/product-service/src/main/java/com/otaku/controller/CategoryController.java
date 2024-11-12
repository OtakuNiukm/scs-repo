package com.otaku.controller;

import com.otaku.domain.Category;
import com.otaku.model.Result;
import com.otaku.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: wz296
 * @Description: 商品类目控制层
 * @Date: Created in 2024/11/5 下午3:03
 * @FileName: CategoryController
 * @Version: 1.0
 */
@Api(tags = "商品类目接口管理")
@RequestMapping("prod/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取所有商品类目
     * @return 商品类目列表
     */
    @ApiOperation("获取所有商品类目")
    @GetMapping("table")
    @PreAuthorize("hasAuthority('prod:category:page')")
    public Result<List<Category>> loadAllCategoryList() {
        List<Category> categoryList = categoryService.queryAllCategoryList();
        return Result.success(categoryList);
    }

    /**
     * 查询系统商品一级类目
     * @return 商品类目列表
     */
    @ApiOperation("查询系统商品一级类目")
    @GetMapping("listCategory")
    @PreAuthorize("hasAuthority('prod:category:page')")
    public Result<List<Category>> loadFirstCategoryList() {
        List<Category> categoryList = categoryService.queryFirstCategoryList();
        return Result.success(categoryList);
    }

    /**
     * 新增商品类目
     * @return 新增结果
     */
    @ApiOperation("新增商品类目")
    @PostMapping
    @PreAuthorize("hasAuthority('prod:category:save')")
    public Result<String> saveCategory(@RequestBody Category category) {
        Boolean saved = categoryService.saveCategory(category);
        return Result.handle(saved);
    }
}
