package com.macro.mall.tiny.config;


import com.macro.mall.tiny.component.JwtAuthenticationTokenFilter;
import com.macro.mall.tiny.component.RestAuthenticationEntryPoint;
import com.macro.mall.tiny.component.RestfulAccessDeniedHandler;
import com.macro.mall.tiny.dto.AdminUserDetails;
import com.macro.mall.tiny.mbg.model.UmsAdmin;
import com.macro.mall.tiny.mbg.model.UmsPermission;
import com.macro.mall.tiny.service.UmsAdminService;
import com.sun.deploy.security.UserDeclinedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @author ovo
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UmsAdminService adminService;
  @Autowired
  private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
  @Autowired
  private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.csrf()
//        .disable()
//        .sessionManagement()  //基于token, 所以不需要session
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .authorizeRequests()
//        .antMatchers(HttpMethod.GET,
//            "/",
//            "/favicon.ico",
//            "/*.html",
//            "/**/*.css",
//            "/**/*.js",
//            "/swagger-resources/**",
//            "/v2/api-docs/**"
//        )
//        .permitAll()
//        .antMatchers("/admin/login", "/admin/register") //对登录或者注册允许匿名访问
//        .permitAll()
//        .antMatchers(HttpMethod.OPTIONS) //跨域的请求会先进行一次options请求
//        .permitAll()
//        .antMatchers("/**") //测试时全部运行访问
//        .permitAll()
//        .anyRequest() //除了上面允许的请求路径 请求方法 和资源外, 所有的请求都需要经过鉴权认证
//        .authenticated();
//    //禁用缓存
//    http.headers().cacheControl(); //禁用缓存
//    // 添加jwt filter
//    http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    //添加自定义未授权和未登录结果返回
//    http.exceptionHandling()
//        .accessDeniedHandler(restfulAccessDeniedHandler)
//        .authenticationEntryPoint(restAuthenticationEntryPoint);
//  }
  
  
  /**
   * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET,
            "/",
            "/fa",
            "/favicon.ico",
            "/*.html",
            "/**/*.css",
            "/**/*.js",
            "/swagger-resources/**",
            "/v2/api-docs/**"
        )
        .permitAll()
        .antMatchers("/admin/login", "/admin/register")
        .permitAll()
        .antMatchers(HttpMethod.OPTIONS)
        .permitAll()
        .antMatchers("/**")
        .permitAll()
        .anyRequest()
        .authenticated();
    http.headers().cacheControl();
    http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService())
        .passwordEncoder(passwordEncoder());
  }
  
  
  /**
   * spring security 安全 中的密码的编码规则不能少
   *
   * @return 编码规则
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
 /* @Bean
  public UserDetailsService userDetailsService() {
    //获取用户登录信息
    return username -> {
      UmsAdmin admin = adminService.getAdminByUsername(username);
      if (admin != null) {
        List<UmsPermission> permissionList = adminService.getPermissionList(admin.getId());
        return new AdminUserDetails(admin, permissionList);
      }
      throw new UsernameNotFoundException("用户名和密码错误");
    };
  }*/
  
  @Bean
  public UserDetailsService userDetailsService() {
    //获取用户登录信息
    return username -> {
      UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
      if (umsAdmin != null) {
        List<UmsPermission> permissionList = adminService.getPermissionList(umsAdmin.getId());
        return new AdminUserDetails(umsAdmin, permissionList);
      }
      throw new UserDeclinedException("用户名和密码错误");
    };
  }
  
  
  @Bean
  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
    return new JwtAuthenticationTokenFilter();
  }
  
  
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}

