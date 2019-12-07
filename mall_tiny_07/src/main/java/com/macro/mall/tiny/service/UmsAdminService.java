package com.macro.mall.tiny.service;

import com.macro.mall.tiny.mbg.model.UmsAdmin;
import com.macro.mall.tiny.mbg.model.UmsPermission;

import java.util.List;

/**
 * @author ovo
 */
public interface UmsAdminService {
  
  UmsAdmin getAdminByUsername(String username);
  
  List<UmsPermission> getPermissionList(Long id);
  
  UmsAdmin register(UmsAdmin umsAdminParam);
  
  String login(String username, String password);
}
 



