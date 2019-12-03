package com.macro.mall.tiny.service.impl;

import com.macro.mall.tiny.mbg.mapper.UmsAdminMapper;
import com.macro.mall.tiny.mbg.model.UmsAdmin;
import com.macro.mall.tiny.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ovo
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
  
  @Autowired
  UmsAdminMapper umsAdminMapper;
  
  @Override
  public UmsAdmin register(UmsAdmin umsAdmin) {
    return null;
  }
}
 

