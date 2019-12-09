package com.macro.mall.tiny.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ovo
 * aliyun:
 * oss:
 * endpoint: oss-cn-shenzhen.aliyuncs.com # oss对外服务的访问域名
 * accessKeyId: test # 访问身份验证中用到用户标识
 * accessKeySecret: test # 用户用于加密签名字符串和oss用来验证签名字符串的密钥
 * bucketName: macro-oss # oss的存储空间
 * policy:
 * expire: 300 # 签名有效期(S)
 * maxSize: 10 # 上传文件大小(M)
 * callback: http://localhost:8080/aliyun/oss/callback # 文件上传成功后的回调地址
 * dir:
 * prefix:
 */
@Configuration
public class OssConfig {
  @Value("${aliyun.oss.endpoint}")
  private String ALIYUN_OSS_ENDPOINT;
  @Value("${aliyun.oss.accessKeyId}")
  private String ALIYUN_OSS_ACCESSKEYID;
  @Value("${aliyun.oss.accessKeySecret}")
  private String ALIYUN_OSS_ACCESSKEYSECRET;
  
  @Bean
  public OSSClient ossClient() {
    return new OSSClient(ALIYUN_OSS_ENDPOINT, ALIYUN_OSS_ACCESSKEYID, ALIYUN_OSS_ACCESSKEYSECRET);
  }
  
}
 

