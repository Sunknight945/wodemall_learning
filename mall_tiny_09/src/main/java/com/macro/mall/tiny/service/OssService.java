package com.macro.mall.tiny.service;

import com.macro.mall.tiny.dto.OssCallbackResult;
import com.macro.mall.tiny.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 *
 * @author ovo
 */

public interface OssService {
  /**
   * oss上传策略生成
   *
   * @return
   */
  OssPolicyResult policy();
  
  
  /**
   * oss上传成功回调
   *
   * @param request
   * @return
   */
  OssCallbackResult callback(HttpServletRequest request);
  
}
