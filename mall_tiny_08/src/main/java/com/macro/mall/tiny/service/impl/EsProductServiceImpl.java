package com.macro.mall.tiny.service.impl;

import com.macro.mall.tiny.dao.EsProductDao;
import com.macro.mall.tiny.nosql.elasticsearch.document.EsProduct;
import com.macro.mall.tiny.nosql.elasticsearch.repository.EsProductRepository;
import com.macro.mall.tiny.service.EsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * 商品搜索管理Service 的实现类
 *
 * @author ovo
 */
@Service
public class EsProductServiceImpl implements EsProductService {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);
  @Autowired
  private EsProductDao productDao;
  
  @Autowired
  private EsProductRepository productRepository;
  
  
  /**
   * 从数据库中导入所有商品到Es
   */
  @Override
  public int importAll() {
    List<EsProduct> esProductList = productDao.getAllEsProductList(null);
    Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
    Iterator<EsProduct> iterator = esProductIterable.iterator();
    int result = 0;
    if (iterator.hasNext()) {
      result++;
      iterator.next();
    }
    return result;
  }
  
  /**
   * 根据id删除商品
   *
   * @param id 商品id
   */
  @Override
  public void delete(Long id) {
    this.productRepository.deleteById(id);
  }
  
  /**
   * 根据Id创建商品
   *
   * @param id 商品id
   * @return 返回商品
   */
  @Override
  public EsProduct create(long id) {
    List<EsProduct> allEsProductList = this.productDao.getAllEsProductList(id);
    EsProduct product = null;
    if (allEsProductList.size() > 0) {
      EsProduct esProduct = allEsProductList.get(0);
      product = this.productRepository.save(esProduct);
    }
    return product;
  }
  
  /**
   * 批量删除商品
   *
   * @param ids 商品ids
   */
  @Override
  public void delete(List<Long> ids) {
    if (!CollectionUtils.isEmpty(ids)) {
      ids.forEach(id -> this.productRepository.deleteById(id));
    }
  }
  
  /**
   * 根据关键字搜索名称或者副标题
   *
   * @param keyword  关键字
   * @param pageNum  页码
   * @param pageSize 分页信息
   * @return 得到的结果
   */
  @Override
  public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageNum, pageSize);
    return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
  }
  
}
 

