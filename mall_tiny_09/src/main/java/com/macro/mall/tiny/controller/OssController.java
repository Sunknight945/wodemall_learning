package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.dto.OssCallbackResult;
import com.macro.mall.tiny.dto.OssPolicyResult;
import com.macro.mall.tiny.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ovo
 */
@RestController
@Api(tags = "OssController", description = "mall中的文件上传接口利用的阿里云oss文件存储的服务")
@RequestMapping("/aliyun/oss")
public class OssController {
  @Autowired
  OssService ossService;
  
  /**
   * <  生成的信息包括但不限于 文件的存储目录, 文件的签名有效期, 文件允许的大小,
   * 文件oss上传成功后后的回调参数OssCallbackParam对象
   * (里面的callbackUrl 请求的会调地址
   * callbackBody 回调是传入request中的参数
   * callbackBodyType 回调时传入参数的格式，比如表单提交形式),
   * 文件设置的提交的节点
   * 文件让 String signature = ossClient.calculatePostSignature(postPolicy);
   * 计算后得出的 signature 的计算机签名 ....
   * >
   * 文件上传的第一步调用本地的方法 仅仅生成文件的策略信息 并不会获取文件 更不会上传文件.
   *
   * @return
   */
  @ApiOperation("oss上传签名生成")
  @GetMapping("/policy")
  public CommonResult<OssPolicyResult> policy() {
    OssPolicyResult result = ossService.policy();
    return CommonResult.success(result);
  }
  
  
  @ApiOperation("oss上传成功回调")
  @PostMapping("callback")
  public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
    OssCallbackResult ossCallbackResult = ossService.callback(request);
    return CommonResult.success(ossCallbackResult);
  }
  
}
 

