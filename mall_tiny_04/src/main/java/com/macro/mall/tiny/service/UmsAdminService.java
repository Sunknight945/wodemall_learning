package com.macro.mall.tiny.service;

import com.macro.mall.tiny.mbg.model.UmsAdmin;
import com.macro.mall.tiny.mbg.model.UmsPermission;

import java.util.List;

/**
 * @author ovo
 */
public interface UmsAdminService {
  /**
   * 注册功能
   * @param umsAdmin
   * @return
   */
  UmsAdmin register(UmsAdmin umsAdmin);
  
  /**
   * 根据用户名获取后台管理员
   * @param username
   * @return
   */
  UmsAdmin getAdminByUsername(String username);
  
  /**
   *  获取用户所有权限（包括角色权限和+-权限）
   * @param adminId
   * @return
   */
  List<UmsPermission> getPermissionList(Long adminId);
  
}
