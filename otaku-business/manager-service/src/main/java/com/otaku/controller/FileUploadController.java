package com.otaku.controller;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.otaku.config.AliyunOssConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;
import java.util.Objects;

/**
 * @Author: wz296
 * @Description: 文件上传接口
 * @Date: Created in 2024/10/30 上午10:19
 * @FileName: FileUploadController
 * @Version: 1.0
 */
@Api(tags = "文件上传接口管理")
@RequestMapping("admin/file")
@RestController
public class FileUploadController {

    @Autowired
    private AliyunOssConfig aliyunOssConfig;

    /**
     * 上传单个文件
     * 1.接口请求必须是post请求
     * 2.接收文件的对象需是MultipartFile，是springMVC提供的
     *
     * @return 文件上传成功后的URL
     */
    @ApiOperation("上传单个文件")
    @PostMapping("upload/element")
    public String uploadFile(MultipartFile file) {

        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = aliyunOssConfig.getEndpoint();

        // 从配置文件中获取AK和SK
        String accessKeyId = aliyunOssConfig.getAccessKeyId();
        String accessKeySecret = aliyunOssConfig.getAccessKeySecret();
        // 从配置文件创建实例
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
//        EnvironmentVariableCredentialsProvider credentialsProvider = null;
//        try {
//            credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//        } catch (com.aliyuncs.exceptions.ClientException e) {
//            throw new RuntimeException(e);
//        }
        // 填写Bucket名称，例如examplebucket。
        String bucketName = aliyunOssConfig.getBucketName();
        // 创建以天为单位的文件夹
        String newFolder = DateUtil.format(new Date(), "yyyyMMdd");
        // 以时间戳为文件名
        String fileName = String.valueOf(System.currentTimeMillis());
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = newFolder + "/" + fileName + Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf('.'));
        // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
        String region = "cn-beijing";

        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        URL url = null;
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file.getInputStream());
            // 上传
            ossClient.putObject(putObjectRequest);
            // 创建上传文件访问的URL
            url = ossClient.generatePresignedUrl(bucketName, objectName, DateUtil.offsetDay(new Date(), 365 * 10));
        } catch (Exception e) {
            System.out.println("Error Message:" + e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        assert url != null;
        return url.toString();
    }
}
