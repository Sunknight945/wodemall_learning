package com.macro.mall.tiny.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ovo
 * 用户建立订单60分钟内未付款就取消订单 并解锁库存的 定时器
 *
 * 用户对某商品进行下单操作；
 * 系统需要根据用户购买的商品信息生成订单并锁定商品的库存；
 * 系统设置了60分钟用户不付款就会取消订单；
 * 开启一个定时任务，每隔10分钟检查下，如果有超时还未付款的订单，就取消订单并取消锁定的商品库存。
 */

@Component
public class OrderTimeoutCancelTask {
  
  public static final Logger LOGGER = LoggerFactory.getLogger(OrderTimeoutCancelTask.class);
  
  public void cancelTimeOutOrder() {
    //Todo: 2019/5/3 此处应调用取消订单的方法，具体查看mall项目源码
    LOGGER.info("取消订单,并且根据sku编号释放锁定库存");
  }
}



 

