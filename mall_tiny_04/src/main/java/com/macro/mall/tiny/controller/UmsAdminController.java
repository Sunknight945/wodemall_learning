package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.dto.UmsAdminLoginParam;
import com.macro.mall.tiny.mbg.model.UmsAdmin;
import com.macro.mall.tiny.mbg.model.UmsPermission;
import com.macro.mall.tiny.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
  
  
  @ApiOperation("登录返回用户的token")
  @PostMapping("/login")
  public CommonResult login(UmsAdminLoginParam adminLoginParam) {
    String token = this.umsAdminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
    if (token == null) {
      return CommonResult.validateFailed("用户名或者密码错误");
    }
    HashMap<String, String> tokenMap = new HashMap<>();
    tokenMap.put("token", token);
    tokenMap.put("tokenHead", tokenHead);
    return CommonResult.success(tokenMap);
    
  }
  
  @ApiOperation("获取用户的所有权限(包括+-权限)")
  @GetMapping("/permission/{adminId}")
  public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable("adminId") Long adminId) {
    List<UmsPermission> permissionList = this.umsAdminService.getPermissionList(adminId);
    return CommonResult.success(permissionList);
  }
  
  
}
 

