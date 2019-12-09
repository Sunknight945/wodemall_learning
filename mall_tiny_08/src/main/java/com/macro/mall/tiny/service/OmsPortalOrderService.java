package com.macro.mall.tiny.service;

import com.macro.mall.tiny.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理Service
 *
 * @author ovo
 */
public interface OmsPortalOrderService {
  
  /**
   * 根据提交信息生成订单
   *
   * @param orderParam
   * @return
   */
  @Transactional
  Object generateOrder(OrderParam orderParam);
  
  
  /**
   * 取消单个超时订单
   */
  @Transactional
  void CancelOrder(Long orderId);
}