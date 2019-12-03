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
 * @author ovo
 */
public class AdminUserDetails implements UserDetails {
  UmsAdmin umsAdmin;
  List<UmsPermission> permissionList;
  
  public AdminUserDetails(UmsAdmin umsAdmin, List<UmsPermission> permissionList) {
    this.umsAdmin = umsAdmin;
    this.permissionList = permissionList;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.permissionList.stream().filter(permission -> permission.getValue() != null)
               .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
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
 

