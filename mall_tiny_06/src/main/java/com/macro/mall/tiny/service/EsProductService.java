package com.macro.mall.tiny.service;

import com.macro.mall.tiny.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 商品搜索管理Service
 *
 * @author ovo
 */
public interface EsProductService {
  
  /**
   * 从数据库中导入所有商品到Es
   */
  int importAll();
  
  
  /**
   * 根据id删除商品
   *
   * @param id 商品id
   */
  void delete(Long id);
  
  /**
   * 根据Id创建商品
   *
   * @param id 商品id
   * @return 返回商品
   */
  EsProduct create(long id);
  
  
  /**
   * 批量删除商品
   *
   * @param ids 商品ids
   */
  void delete(List<Long> ids);
  
  /**
   * 根据关键字搜索名称或者副标题
   *
   * @param keyword 关键字
   * @param pageNum 页码
   * @param pageSize 分页信息
   * @return 得到的结果
   */
  Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
