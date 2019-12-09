package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.nosql.elasticsearch.document.EsProduct;
import com.macro.mall.tiny.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ovo
 */
@Api(tags = "EsProductController", description = "搜索商品管理")
@RestController("/esProduct")
public class EsProductController {
  private static final Logger LOGGER = LoggerFactory.getLogger(EsProductService.class);
  @Autowired
  EsProductService esProductService;
  
  @ApiOperation(value = "导入所有数据库中的商品到ES")
  @PostMapping("/importAll")
  public CommonResult<Integer> importAllList() {
    int count = this.esProductService.importAll();
    return CommonResult.success(count);
  }
  
  @ApiOperation("根据id删除商品")
  @PostMapping("/delete/{id}")
  public CommonResult<Object> delete(@PathVariable("id") Long id) {
    this.esProductService.delete(id);
    return CommonResult.success(null);
  }
  
  @ApiOperation("根据id批量删除商品")
  @PostMapping("/delete/batch")
  public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
    this.esProductService.delete(ids);
    return CommonResult.success(null);
  }
  
  @ApiOperation("根据id创建商品")
  @PostMapping("/create/{id}")
  public CommonResult<EsProduct> create(@PathVariable("id") Long id) {
    EsProduct esProduct = this.esProductService.create(id);
    if (esProduct != null) {
      return CommonResult.success(esProduct);
    } else {
      return CommonResult.failed("创建失败");
    }
  }
  
  @ApiOperation("简单搜索")
  @GetMapping("/search")
  public CommonResult<CommonPage<EsProduct>> search(@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
                                                    @RequestParam(value = "keyword", required = false) String keyword) {
    Page<EsProduct> esProductPage = this.esProductService.search(keyword, pageNum, pageSize);
    return CommonResult.success(CommonPage.restPage(esProductPage));
  }
  
}
 

