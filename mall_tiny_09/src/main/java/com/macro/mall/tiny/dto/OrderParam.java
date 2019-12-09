package com.macro.mall.tiny.dto;

/**
 * @author ovo
 */

public class OrderParam {
  
  /**
   * 用户收货地址id
   */
  private Long memberReceiveAddressId;
  
  /**
   * 优惠券id
   */
  private Long couponId;
  
  /**
   * 使用积分
   */
  private Integer useIntegration;
  
  /**
   * 支付方式
   */
  private Integer payType;
  
  public Long getMemberReceiveAddressId() {
    return memberReceiveAddressId;
  }
  
  public void setMemberReceiveAddressId(Long memberReceiveAddressId) {
    this.memberReceiveAddressId = memberReceiveAddressId;
  }
  
  public Long getCouponId() {
    return couponId;
  }
  
  public void setCouponId(Long couponId) {
    this.couponId = couponId;
  }
  
  public Integer getUseIntegration() {
    return useIntegration;
  }
  
  public void setUseIntegration(Integer useIntegration) {
    this.useIntegration = useIntegration;
  }
  
  public Integer getPayType() {
    return payType;
  }
  
  public void setPayType(Integer payType) {
    this.payType = payType;
  }
}
 

