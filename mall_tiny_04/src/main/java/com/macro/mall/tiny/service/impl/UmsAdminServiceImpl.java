package com.macro.mall.tiny.service.impl;

import com.macro.mall.tiny.common.utils.JwtTokenUtil;
import com.macro.mall.tiny.dao.UmsAdminRoleRelationDao;
import com.macro.mall.tiny.mbg.mapper.UmsAdminMapper;
import com.macro.mall.tiny.mbg.model.UmsAdmin;
import com.macro.mall.tiny.mbg.model.UmsAdminExample;
import com.macro.mall.tiny.mbg.model.UmsPermission;
import com.macro.mall.tiny.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author ovo
 */
public class UmsAdminServiceImpl implements UmsAdminService {
  static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
  
  @Autowired
  UmsAdminMapper adminMapper;
  
  @Autowired
  UmsAdminRoleRelationDao umsAdminRoleRelationDao;
  
  @Value("${jwt.tokenHeader}")
  String tokenHeader;
  
  @Autowired
  PasswordEncoder passwordEncoder;
  
  @Autowired
  UserDetailsService userDetailsService;
  
  @Autowired
  JwtTokenUtil jwtTokenUtil;
  
  
  @Override
  public UmsAdmin register(UmsAdmin umsAdminParam) {
    UmsAdmin umsAdmin = new UmsAdmin();
    BeanUtils.copyProperties(umsAdminParam, umsAdmin);
    umsAdmin.setCreateTime(new Date());
    umsAdmin.setStatus(1);
    //查询是否有相同用户名的用户
    UmsAdminExample example = new UmsAdminExample();
    example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
    List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
    if (!CollectionUtils.isEmpty(umsAdminList)) {
      return null;
    }
    //将密码进行加密操作
    String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
    umsAdmin.setPassword(encodePassword);
    adminMapper.insert(umsAdmin);
    return umsAdmin;
  }
  
  /**
   * 根据用户名获取后台管理员
   */
  @Override
  public UmsAdmin getAdminByUsername(String username) {
    UmsAdminExample example = new UmsAdminExample();
    example.createCriteria().andUsernameEqualTo(username);
    List<UmsAdmin> umsAdminList = this.adminMapper.selectByExample(example);
    if (!CollectionUtils.isEmpty(umsAdminList)) {
      return umsAdminList.get(0);
    }
    return null;
  }
  
  /**
   * 获取用户所有权限（包括角色权限和+-权限）
   *
   * @param adminId@return
   */
  @Override
  public List<UmsPermission> getPermissionList(Long adminId) {
    return umsAdminRoleRelationDao.getPermissionList(adminId);
  }
  
  
}
 

