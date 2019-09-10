package com.samoy.fabiaoqing.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * AliyunUtils
 *
 * @author Samoy
 * @date 2019/9/10
 */
@Component
@Slf4j
public class OssUtils {
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.bucketName}")
    private String bucketName;
    @Value("${aliyun.endpoint}")
    private String endpoint;
    @Value("${aliyun.avatarDir}")
    private String avatarDir;

    /**
     * 上传头像
     *
     * @param file 文件
     * @return 头像url
     */
    public String uploadAvatar(MultipartFile file) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String fileUrl = generatorAvatarUrl(file.getOriginalFilename());
        try {
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file.getInputStream()));
            return result == null ? "" : "https://" + bucketName + "." + endpoint + "/" + fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 生成头像url
     *
     * @param filename 文件名称
     * @return url
     */
    private String generatorAvatarUrl(String filename) {
        return avatarDir + "/" + System.currentTimeMillis() + "_" + filename;
    }
}
