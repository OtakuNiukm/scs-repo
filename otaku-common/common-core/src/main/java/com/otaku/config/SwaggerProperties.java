package com.otaku.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: wz296
 * @Description: Swagger配置属性对象
 * @Date: Created in 2024/10/15 下午4:15
 * @FileName: SwaggerProperties
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "swagger3")
public class SwaggerProperties {

    /**
     * 基础包名，用于指定需要扫描的组件包路径
     * 该字段通常用于配置Spring的包扫描，决定哪些组件会被自动配置和实例化
     */
    private String basePackage;

    /**
     * 作者名称
     */
    private String name;

    // 定义API的URL地址
    private String url;

    // 定义联系人的电子邮件地址
    private String email;

    // 定义API的标题
    private String title;

    // 定义API的描述信息
    private String description;

    // 定义API的许可类型
    private String license;

    // 定义许可证的URL地址
    private String licenseUrl;

    // 定义服务条款的URL地址
    private String termsOfServiceUrl;

    // 版本号
    private String version;




}
