package com.macro.mall.tiny.dto;

/**
 * @author ovo
 * 消息队列枚举配置
 */
public enum QueueEnum {
  /**
   * 消息通知队列
   */
  QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel", "mall.order.cancel"),
  /**
   * 消息通知ttl队列
   */
  QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl", "mall.order.cancel.ttl", "mall.order.cancel.ttl");
  
  /**
   * 交换机名称
   */
  private String exchange;
  
  /**
   * 列队名称
   */
  private String name;
  
  /**
   * 路由键
   */
  private String routingKey;
  
  public String getExchange() {
    return exchange;
  }
  
  public void setExchange(String exchange) {
    this.exchange = exchange;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getRoutingKey() {
    return routingKey;
  }
  
  public void setRoutingKey(String routingKey) {
    this.routingKey = routingKey;
  }
  
  QueueEnum(String exchange, String name, String routingKey) {
    this.exchange = exchange;
    this.name = name;
    this.routingKey = routingKey;
  }
}
