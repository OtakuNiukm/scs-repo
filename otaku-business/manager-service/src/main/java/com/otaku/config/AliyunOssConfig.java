package com.otaku.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Author: wz296
 * @Description: 阿里云OSS配置
 * @Date: Created in 2024/10/30 上午10:51
 * @FileName: AliyunOssConfig
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
@RefreshScope
public class AliyunOssConfig {

    /**
     * 阿里云OSS Endpoint
     */
    private String endpoint;

    /**
     * 阿里云OSS bucketName
     */
    private String bucketName;

    /**
     * 阿里云OSS accessKeyId
     */
    private String accessKeyId;

    /**
     * 阿里云OSS accessKeySecret
     */
    private String accessKeySecret;

}
