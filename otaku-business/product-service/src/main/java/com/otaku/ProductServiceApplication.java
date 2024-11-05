package com.otaku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: wz296
 * @Description: 产品业务模块启动类
 * @Date: Created in 2024/11/5 下午2:53
 * @FileName: ProductServiceApplication
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
