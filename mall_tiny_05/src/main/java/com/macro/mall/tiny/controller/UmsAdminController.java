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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 *
 * @author ovo
 */
@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
  
  @Autowired
  private UmsAdminService umsAdminService;
  
  @Autowired
  private UserDetailsService userDetailsService;
  
  /**
   * jwt:
   * tokenHeader: Authorization #JWT存储的请求头
   * secret: mySecret #JWT加解密使用的密钥
   * expiration: 604800 #JWT的超期限时间(60*60*24)
   * tokenHead: Bearer  #JWT负载中拿到开头
   */
  @Value("${jwt.tokenHeader}")
  private String tokenHeader;
  
  @Value("${jwt.tokenHead}")
  private String tokenHead;
  
  
  @PostMapping("/register")
  @ApiOperation(value = "用户注册")
  public CommonResult<UmsAdmin> register(UmsAdmin umsAdminParam, BindingResult result) {
    UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
    if (umsAdmin == null) {
      CommonResult.failed();
    }
    return CommonResult.success(umsAdmin);
  }
  
  @PostMapping("/login")
  @ApiOperation(value = "登录成功后返回token")
  public CommonResult login(UmsAdminLoginParam adminLoginParam) {
    String token = this.umsAdminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
    if (token == null) {
      return CommonResult.validateFailed("用户名或密码错误");
    }
    Map<String, String> tokenMap = new HashMap<>();
    tokenMap.put("token", token);
    tokenMap.put("tokenHead", tokenHead);
    return CommonResult.success(token);
  }
  
  
  @GetMapping("/permission/{adminId}")
  @ApiOperation("获取用户所有权限（包括+-权限）")
  public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable("adminId") Long adminId) {
    List<UmsPermission> permissionList = this.umsAdminService.getPermissionList(adminId);
    return CommonResult.success(permissionList);
  }
  
}
 

