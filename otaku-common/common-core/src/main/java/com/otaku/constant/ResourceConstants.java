package com.otaku.constant;

/**
 * @Author: wz296
 * @Description: 资源服务器常量
 * @Date: Created in 2024/10/16 下午1:42
 * @FileName: a
 * @Version: 1.0
 */
public interface ResourceConstants {
    /**
     * 允许访问的资源路径
     */
    String[] RESOURCE_ALLOW_URLS = {
            "/v2/api-docs",  // swagger
            "/v3/api-docs",
            "/swagger-resources/configuration/ui",  //用来获取支持的动作
            "/swagger-resources",                   //用来获取api-docs的URI
            "/swagger-resources/configuration/security",//安全选项
            "/webjars/**",
            "/swagger-ui/**",
            "/druid/**",
            "/actuator/**"
    };
}
