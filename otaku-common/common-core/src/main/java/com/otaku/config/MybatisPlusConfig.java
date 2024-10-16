package com.otaku.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wz296
 * @Description: 分页插件
 * @Date: Created in 2024/10/15 下午3:40
 * @FileName: MyBatisPlusConfig
 * @Version: 1.0
 */
@Configuration
@MapperScan("com.otaku.mapper")
public class MybatisPlusConfig {

    /**
     * 添加分页插件
     *
     * 此方法配置MybatisPlus的拦截器，用于在数据库查询时添加分页功能
     * 通过拦截器，可以在执行数据库查询之前添加分页逻辑，从而方便地实现分页查询
     *
     * @return 返回配置好的MybatisPlusInterceptor实例，该实例包含分页拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 如果配置多个插件, 切记分页最后添加
        // 如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
        return interceptor;
    }
}