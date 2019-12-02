package com.macro.mall.tiny.controller;


import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.PmsBrand;
import com.macro.mall.tiny.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理Controller
 *
 * @author ovo
 */
@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
  @Autowired
  PmsBrandService demoService;
  
  private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);
  
  @ApiOperation("获取所有品牌列表")
  @GetMapping("/listAll")
  public CommonResult<List<PmsBrand>> getBrandList() {
    return CommonResult.success(this.demoService.getBrandList());
  }
  
  @ApiOperation("添加新建品牌")
  @PostMapping("/create")
  public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
    CommonResult commonResult;
    int count = demoService.createBrand(pmsBrand);
    if (count == 1) {
      commonResult = CommonResult.success(pmsBrand);
      LOGGER.debug("createBrand success {}", pmsBrand);
    } else {
      commonResult = CommonResult.failed("操作失败");
      LOGGER.debug("createBrand failed {}", pmsBrand);
    }
    return commonResult;
  }
  
  @ApiOperation("按品牌Id更新传来的品牌信息")
  @PutMapping("/update/{id}")
  public CommonResult update(@PathVariable("id") Long branId, @RequestBody PmsBrand pmsBrandDto) {
    CommonResult commonResult;
    int count = demoService.update(branId, pmsBrandDto);
    if (count == 1) {
      commonResult = CommonResult.success(pmsBrandDto);
      LOGGER.debug("updateBrand success {}", pmsBrandDto);
    } else {
      commonResult = CommonResult.failed("操作失败");
      LOGGER.debug("updateBrand failed {}", pmsBrandDto);
    }
    return commonResult;
  }
  
  @ApiOperation("按id删除单个品牌")
  @DeleteMapping("/delete/{id}")
  public CommonResult deleteBrand(@PathVariable("id") Long id) {
    int i = this.demoService.deleteBrand(id);
    if (i == 1) {
      LOGGER.debug("deleteBrand success : id={}", id);
      return CommonResult.success(null);
    } else {
      LOGGER.debug("deleteBrand failed : id={}", id);
      return CommonResult.failed("操作失败");
    }
  }
  
  
  @ApiOperation("分页查询品牌列表")
  @GetMapping("/list")
  public CommonResult<CommonPage<PmsBrand>> getListBrandByPage(
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
    List<PmsBrand> brandList = this.demoService.listBrand(pageNum, pageSize);
    return CommonResult.success(CommonPage.rest(brandList));
  }
  
  @ApiOperation("按id获取单个品牌")
  @GetMapping("/{id}")
  public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
    return CommonResult.success(demoService.getbrand(id));
  }
  
}
 

