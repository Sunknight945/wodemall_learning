package com.macro.mall.tiny.common.api;


/**
 * 通用返回对象
 * @author ovo
 */
public class CommonResult<T> {
  private Long code;
  private String message;
  private T data;
  
  protected CommonResult() {
  }
  
  protected CommonResult(Long code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }
  
  public Long getCode() {
    return code;
  }
  
  public void setCode(Long code) {
    this.code = code;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public T getData() {
    return data;
  }
  
  public void setData(T data) {
    this.data = data;
  }
  
  
  public static <T> CommonResult<T> success(T data) {
    return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
  }
  
  
  public static <T> CommonResult<T> success(T data, String message) {
    return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
  }
  
  
  public static <T> CommonResult<T> failed(IErrorCode errorCode) {
    return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
  }
  
  public static <T> CommonResult<T> failed(String message) {
    return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
  }
  
  public static <T> CommonResult<T> failed() {
    return failed(ResultCode.FAILED);
  }
  
  public static <T> CommonResult<T> validateFailed() {
    return failed(ResultCode.VALIDATE_FAILED);
  }
  
  public static <T> CommonResult<T> validateFailed(String message) {
    return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
  }
  
  public static <T> CommonResult<T> unauthorized(T data) {
    return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
  }
  
  public static <T> CommonResult<T> forbidden(T data) {
    return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
  }
  
}
 

