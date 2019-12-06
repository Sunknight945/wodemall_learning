package com.macro.mall.tiny.service.impl;

import com.macro.mall.tiny.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ovo
 */
@Service
public class RedisServiceImpl implements RedisService {
  
  
  @Autowired
  StringRedisTemplate stringRedisTemplate;
  
  
  /**
   * 存储数据
   */
  @Override
  public void set(String key, String value) {
    this.stringRedisTemplate.opsForValue().set(key, value);
  }
  
  /**
   * 获取数据
   */
  @Override
  public String get(String key) {
    return this.stringRedisTemplate.opsForValue().get(key);
  }
  
  /**
   * 设置超时时间
   */
  @Override
  public boolean expire(String key, long expire) {
    return this.stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
  }
  
  /**
   * 删除数据
   */
  @Override
  public void remove(String key) {
    this.stringRedisTemplate.delete(key);
  }
  
  /**
   * 自增操作
   */
  @Override
  public Long increment(String key, long delta) {
    return this.stringRedisTemplate.opsForValue().increment(key, delta);
  }
}
 

