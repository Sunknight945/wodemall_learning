package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ovo
 */
//@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {
  

  @Autowired
  UmsMemberService memberService;
  
//  @ApiOperation("获取验证码")
  @GetMapping("/getAuthCode")
  public CommonResult getAuthCode(@RequestParam String telephone) {
    return memberService.generateAuthCode(telephone);
  }
  
  
//  @ApiOperation("判断验证码是否正确")
  @PostMapping("/verifyAuthCode")
  public CommonResult updatePassword(@RequestParam String telephone,
                                     @RequestParam String authCode) {
    return memberService.verifyAuthCode(telephone, authCode);
  }
  
  
}
 

