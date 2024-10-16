package com.otaku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;


@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheConfiguration redisCacheConfig(){
        // 创建Redis缓存配置类
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        // 设置Redis
        redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json())
                ) // 设置序列化器
                .entryTtl(Duration.ofDays(7))   // 设置缓存有效期7天
                .disableCachingNullValues();   // redis 不缓存空值

        return redisCacheConfiguration;
    }
}
