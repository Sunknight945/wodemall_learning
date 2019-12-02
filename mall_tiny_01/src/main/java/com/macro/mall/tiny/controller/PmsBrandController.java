package com.macro.mall.tiny.controller;


import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.PmsBrand;
import com.macro.mall.tiny.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌管理Controller
 *
 * @author ovo
 */

@RestController
@RequestMapping("/brand")
public class PmsBrandController {
  @Autowired
  PmsBrandService demoService;
  
  private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);
  
  @GetMapping("/listAll")
  public CommonResult<List<PmsBrand>> getBrandList() {
    return CommonResult.success(this.demoService.getBrandList());
  }
  
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
  
  @PutMapping("/update/{id}")
  public CommonResult update(@PathVariable("id") Long branId, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
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
  
  
  @GetMapping("/list")
  public CommonResult<CommonPage<PmsBrand>> getListBrandByPage(
      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
    CommonResult commonResult;
    List<PmsBrand> brandList = this.demoService.listBrand(pageNum, pageSize);
    if (CollectionUtils.isEmpty(brandList)) {
      commonResult = CommonResult.failed("操作失败");
      LOGGER.debug("getBrandListByPage failed");
    }
    return CommonResult.success(CommonPage.rest(brandList));
  }
  
  @GetMapping("/{id}")
  public CommonResult<PmsBrand> brand(@PathVariable("id") Long id){
    return CommonResult.success(demoService.getbrand(id));
  }
  
}
 

