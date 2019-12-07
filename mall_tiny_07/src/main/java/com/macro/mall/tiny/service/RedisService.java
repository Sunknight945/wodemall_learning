package com.macro.mall.tiny.service;

/**
 * redis操作service
 * 对象和数组都是一json形式进行存储
 *
 * @author ovo
 */
public interface RedisService {
  /**
   *  存储数据
   * @param key
   */
  void set(String key,String value);
  
  /**
   * 获取数据
   * @param key
   */
  String get(String key);
  
  /**
   * 设置超时时间
   * @param key
   * @param expire
   * @return
   */
  boolean expire(String key, long expire);
  
  
  /**
   * 删除数据
   * @param key
   */
  void remove(String key);
  
  /**
   * 自增操作
   * @param delta 自增步长
   */
  Long increment(String key,long delta);

}
