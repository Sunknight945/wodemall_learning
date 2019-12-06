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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * UmsAdminService实现类
 *
 * @author ovo
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
  public static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Value("${jwt.tokenHead}")
  private String tokenHead;
  @Autowired
  private UmsAdminMapper adminMapper;
  @Autowired
  private UmsAdminRoleRelationDao adminRoleRelationDao;
  
  
  @Override
  public UmsAdmin getAdminByUsername(String username) {
    UmsAdminExample example = new UmsAdminExample();
    example.createCriteria().andUsernameEqualTo(username);
    List<UmsAdmin> umsAdmins = this.adminMapper.selectByExample(example);
    if (umsAdmins != null && umsAdmins.size() > 0) {
      return umsAdmins.get(0);
    } else {
      return null;
    }
  }
  
  
  @Override
  public UmsAdmin register(UmsAdmin umsAdminParam) {
    UmsAdmin umsAdmin = new UmsAdmin();
    BeanUtils.copyProperties(umsAdminParam, umsAdmin);
    umsAdmin.setCreateTime(new Date());
    umsAdmin.setStatus(1);
    //查询是否有相同的用户名
    UmsAdminExample example = new UmsAdminExample();
    example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
    List<UmsAdmin> umsAdminList = this.adminMapper.selectByExample(example);
    if (!CollectionUtils.isEmpty(umsAdminList)) {
      return null;
    }
    //将密码进行加密操作
    umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
    this.adminMapper.insertSelective(umsAdmin);
    return umsAdmin;
  }
  
  @Override
  public String login(String username, String password) {
    String token = null;
    try {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        return null;
      }
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      token = jwtTokenUtil.generateToken(userDetails);
    } catch (UsernameNotFoundException e) {
      LOGGER.warn("登录出现异常 异常信息: {}", e.getMessage());
    }
    return token;
  }
  
  
  @Override
  public List<UmsPermission> getPermissionList(Long adminId) {
    return this.adminRoleRelationDao.getPermissionList(adminId);
  }
  
}
 

