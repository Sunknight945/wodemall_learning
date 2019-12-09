package com.macro.mall.tiny.service.impl;

/**
 * 前台订单管理service
 *
 * @author ovo
 */

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.component.CancelOrderSender;
import com.macro.mall.tiny.dto.OrderParam;
import com.macro.mall.tiny.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
  private static final Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);
  /**
   * 引入 CancelOrderSender 需要他来发送ttl消息去取消到时间后没有及时支付的订单 解锁他们的库存信息
   */
  @Autowired
  CancelOrderSender cancelOrderSender;
  
  /**
   * 根据提交信息生成订单
   *
   * @param orderParam
   * @return
   */
  @Override
  public Object generateOrder(OrderParam orderParam) {
    // todo :  odo 执行一系类下单操作，具体参考mall项目
    // todo :  这一些下单操作 需要 一系列的数据如金额, 地址等等. 然后还需要调用支付宝,银行,或者微信的支付接口.
    LOGGER.info("生成订单");
    //下单在生成订单后开启一个延时消息,及时处理(取消)应到时间后没有及时支付的订单
    sendDelayMessageCancelOrder(11L);
    return CommonResult.success(null, "下单成功");
  }
  
  private void sendDelayMessageCancelOrder(long orderId) {
    //获取订单的超时时间, 假设为60分钟,(开发阶段设置为30秒)
    long delayTimes =  10*1000;
    //发送ttl延时消息(主备到时间后让mq通知其他接口主备取消订单)
    this.cancelOrderSender.sendMessage(orderId, delayTimes);
  }
  
  /**
   * 取消单个超时订单
   *
   * @param orderId
   */
  @Override
  public void CancelOrder(Long orderId) {
    //todo 执行一系列的操作后, 取消订单操作, 具体参考mall项目
    LOGGER.info("process cancelOrder orderId:(process 取消订单 订单号为:{})", orderId);
  }
}