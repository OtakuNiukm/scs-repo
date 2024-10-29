package com.otaku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: wz296
 * @Description: 系统管理模块启动类
 * @Date: Created in 2024/10/16 下午3:45
 * @FileName: ManagerServiceApplication
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching // 开启缓存功能（默认使用缓存中间件为Redis）
public class ManagerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerServiceApplication.class, args);
    }

    /**
     * Spring Security框架中的密码加密器
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
