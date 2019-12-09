package com.macro.mall.tiny.component;


import com.macro.mall.tiny.dto.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ovo
 * 订单创建号之后在OmsPortalOrderServiceImpl实现类
 * 里面传参调用 利用 MQ延时取消未支付订单
 */
@Component
public class CancelOrderSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);
  
  @Autowired
  AmqpTemplate amqpTemplate;
  
  /**
   * 利用 MQ延时消息取消订单
   *
   * @param orderId
   * @param delayTimes
   */
  public void sendMessage(long orderId, long delayTimes) {
    //发送delayMessage
    amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
        QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRoutingKey(),
        orderId, new MessagePostProcessor() {
          @Override
          public Message postProcessMessage(Message message) throws AmqpException {
            message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
            return message;
          }
        });
    LOGGER.info("send delay message orderId:{}->下面的一样",orderId);
    LOGGER.info("发送 取消定的 mq 消息 并且已设置 延时处理的时间 orderId 与 延时的时间分别是 orderId:{} -> delayTimes:{}(毫秒)", orderId, delayTimes);
  }
}