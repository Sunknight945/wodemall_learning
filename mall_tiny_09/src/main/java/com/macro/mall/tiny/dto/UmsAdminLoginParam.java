package com.macro.mall.tiny.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author ovo
 */
public class UmsAdminLoginParam {
  @ApiModelProperty(value = "密码", required = true)
  @NotEmpty(message = "用户名不能为空")
  String password;
  @ApiModelProperty(value = "用户名", required = true)
  @NotEmpty(message = "密码不能为空")
  String username;
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
}
 

