package com.macro.mall.tiny.dto;

import com.macro.mall.tiny.mbg.model.UmsAdmin;
import com.macro.mall.tiny.mbg.model.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 *
 * @author ovo
 */
public class AdminUserDetails implements UserDetails {
  private UmsAdmin umsAdmin;
  private List<UmsPermission> umsPermissions;
  
  public AdminUserDetails(UmsAdmin umsAdmin, List<UmsPermission> umsPermissions) {
    this.umsAdmin = umsAdmin;
    this.umsPermissions = umsPermissions;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //返回当前用户的权限
    return umsPermissions.stream()
               .filter(umsPermission -> umsPermission.getValue() != null)
               .map(umsPermission -> new SimpleGrantedAuthority(umsPermission.getValue()))
               .collect(Collectors.toList());
  }
  
  @Override
  public String getPassword() {
    return umsAdmin.getPassword();
  }
  
  @Override
  public String getUsername() {
    return umsAdmin.getUsername();
  }
  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return umsAdmin.getStatus().equals(1);
  }
}
 

