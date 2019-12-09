package com.macro.mall.tiny.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.macro.mall.tiny.dto.OssCallbackParam;
import com.macro.mall.tiny.dto.OssCallbackResult;
import com.macro.mall.tiny.dto.OssPolicyResult;
import com.macro.mall.tiny.service.OssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * oss上传管理Service实现类
 *
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
 * prefix: mall/images/ # 上传文件夹路径前缀
 */
@Service
public class OssServiceImpl implements OssService {
  private static final Logger LOGGER = LoggerFactory.getLogger(OssServiceImpl.class);
  @Value("${aliyun.oss.policy.expire}")
  private int ALIYUN_OSS_EXPIRE;
  @Value("${aliyun.oss.maxSize}")
  private int ALIYUN_OSS_MAX_SIZE;
  @Value("${aliyun.oss.callback}")
  private String ALIYUN_OSS_CALLBACK;
  @Value("${aliyun.oss.bucketName}")
  private String ALIYUN_OSS_BUCKET_NAME;
  @Value("${aliyun.oss.endpoint}")
  private String ALIYUN_OSS_ENDPOINT;
  @Value("${aliyun.oss.dir.prefix}")
  private String ALIYUN_OSS_DIR_PREFIX;
  
  @Autowired
  OSSClient ossClient;
  
  /**
   * oss上传策略生成
   * 签名生成 OssPolicyResult result
   * <oss上传 需要的信息有
   * 1.存储目录
   * 2.签名有效期
   * 3.可以上传文件的大小(最大和最小)
   * 4.回调 回调对象需要设置以下的参数(OssCallbackParam callback)
   * callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&with=${image.width}");
   * callback.setCallbackBodyType("application/x-www-form-urlencoded");
   * 利用 aliyun sdk 自带的  BinaryUtil工具类和JSONUtils工具类将callback对象重新编码成在可以在httpServletRequest中传输的String类型的字符串.
   * String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callback).toString().getBytes("utf-8"));
   * 5.提交节点
   * 6.计算后签名
   * >
   * //返回结果
   * result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
   * result.setPolicy(postPolicy);
   * result.setSignature(signature);
   * result.setDir(dir);
   * result.setCallback(callbackData);
   * result.setHost(action);
   * oss上传成功回调
   *
   * @return 上传文件之前先要生成policy 并且获取这个 policy文件政策(策略)
   *
   */
  @Override
  public OssPolicyResult policy() {
    OssPolicyResult result = new OssPolicyResult();
    //存储目录
    SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
    String dir = ALIYUN_OSS_BUCKET_NAME + sdf.format(new Date());
    //签名有效期
    long expireEndTime = System.currentTimeMillis() + ALIYUN_OSS_EXPIRE * 1000;
    Date expiration = new Date(expireEndTime);
    //文件大小
    int maxSize = ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
    //回调
    OssCallbackParam callback = new OssCallbackParam();
    callback.setCallbackUrl(ALIYUN_OSS_CALLBACK);
    callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&with=${image.width}");
    callback.setCallbackBodyType("application/x-www-form-urlencoded");
    //提交节点(host)
    String action = "http://" + ALIYUN_OSS_BUCKET_NAME + "." + ALIYUN_OSS_ENDPOINT;
    try {
      PolicyConditions policyConds = new PolicyConditions();
      policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
      policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
      String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
      byte[] binaryData = postPolicy.getBytes("utf-8");
      //计算后签名
      String signature = ossClient.calculatePostSignature(postPolicy);
      String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callback).toString().getBytes("utf-8"));
      //返回结果
      result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
      result.setPolicy(postPolicy);
      result.setSignature(signature);
      result.setDir(dir);
      result.setCallback(callbackData);
      result.setHost(action);
    } catch (UnsupportedEncodingException e) {
      LOGGER.error("签名生成失败", e);
    }
    return result;
  }
  
  /**
   * oss上传成功回调
   *
   * @param request
   * @return
   */
  @Override
  public OssCallbackResult callback(HttpServletRequest request) {
    OssCallbackResult result = new OssCallbackResult();
    String filename = request.getParameter("filename");
    filename = "http://".concat(ALIYUN_OSS_BUCKET_NAME).concat(".").concat(ALIYUN_OSS_ENDPOINT).concat("/").concat(filename);
    result.setFilename(filename);
    result.setSize(request.getParameter("size"));
    result.setMimeType(request.getParameter("mimeType"));
    result.setWidth(request.getParameter("width"));
    result.setHeight(request.getParameter("height"));
    return result;
  }
}
 

