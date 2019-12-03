package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.UmsAdmin;
import com.macro.mall.tiny.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ovo
 */
@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
  /**
   * jwt:
   * tokenHeader: Authorization #JWT存储的请求头
   * secret: mySecret #JWT加解密使用的密钥
   * expiration: 604800 #JWT的超期限时间(60*60*24)
   * tokenHead: Bearer  #JWT负载中拿到开头
   */
  
  @Autowired
  UmsAdminService umsAdminService;
  @Value("${jwt.tokenHeader}")
  String tokenHeader;
  @Value("${jwt.tokenHeader}")
  String tokenHead;
  
  @PostMapping("/register")
  @ApiOperation(value = "用户注册")
  public CommonResult<UmsAdmin> register(UmsAdmin umsAdmin) {
    UmsAdmin newUmsAdmin = this.umsAdminService.register(umsAdmin);
    if (newUmsAdmin == null) {
      return CommonResult.failed();
    } else {
      return CommonResult.success(umsAdmin);
    }
  }
  
  
}
 

