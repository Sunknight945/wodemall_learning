package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.dto.OrderParam;
import com.macro.mall.tiny.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ovo
 */

@RestController
@Api(tags = "OmsPortalOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsPortalOrderController {
  
  @Autowired
  private OmsPortalOrderService portalOrderService;
  
  
  @ApiOperation("根据购物车信息生成订单")
  @PostMapping("/generatedOrderId")
  public Object generateOrder(OrderParam orderParam) {
    //todo 还有一系列的操作 把订单里面的sku的id价格什么的东西存入数据库锁定起来, 知道支付成功完成订单 或者 到时间之后 订单取消 解除锁定的库存等信息.
    return this.portalOrderService.generateOrder(orderParam);
  }
}