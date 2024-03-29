package com.macro.mall.tiny.component;

import com.macro.mall.tiny.common.api.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ovo
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
  
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
    response.setCharacterEncoding("utf-8");
    response.setContentType("application/json");
    response.getWriter().println(CommonResult.forbidden(e.getMessage()));
    response.getWriter().flush();
  }
}
 

