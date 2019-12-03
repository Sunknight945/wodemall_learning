package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.dto.UmsAdminLoginParam;
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
  
  @Autowired
  UmsAdminService umsAdminService;
  @Value("${jwt.tokenHeader}")
  String tokenHeader;
  @Value("${jwt.tokenHead}")
  String tokenHead;
  
  @ApiOperation(value = "用户注册")
  @PostMapping("/register")
  public CommonResult<UmsAdmin> register(UmsAdmin umsAdmin) {
    UmsAdmin newUmsAdmin = this.umsAdminService.register(umsAdmin);
    if (newUmsAdmin == null) {
      return CommonResult.failed();
    } else {
      return CommonResult.success(umsAdmin);
    }
  }
  
  
  @ApiOperation("登录以后返回token")
  @PostMapping("/login")
  public CommonResult login(UmsAdminLoginParam umsAdminLoginParam){
  
  }
  
  
  
}
 

