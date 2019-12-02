package com.macro.mall.tiny.service;

import com.macro.mall.tiny.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author ovo
 */
public interface PmsBrandService {
  
  List<PmsBrand> getBrandList();
  
  int createBrand(PmsBrand pmsBrand);
  
  int update(Long branId, PmsBrand pmsBrandDto);
  
  List<PmsBrand> listBrand(Integer pageNum, Integer pageSize);
  
  PmsBrand getbrand(Long id);
}
