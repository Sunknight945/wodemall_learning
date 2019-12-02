package com.macro.mall.tiny.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.tiny.mbg.mapper.PmsBrandMapper;
import com.macro.mall.tiny.mbg.model.PmsBrand;
import com.macro.mall.tiny.mbg.model.PmsBrandExample;
import com.macro.mall.tiny.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author ovo
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
  @Autowired
  PmsBrandMapper pmsBrandMapper;
  
  @Override
  public List<PmsBrand> getBrandList() {
    return pmsBrandMapper.selectByExample(new PmsBrandExample());
  }
  
  @Override
  public int createBrand(PmsBrand pmsBrand) {
    return this.pmsBrandMapper.insertSelective(pmsBrand);
  }
  
  @Override
  public int update(Long branId, PmsBrand pmsBrandDto) {
    pmsBrandDto.setId(branId);
    return this.pmsBrandMapper.updateByPrimaryKey(pmsBrandDto);
  }
  
  @Override
  public List<PmsBrand> listBrand(Integer pageNum, Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    return this.pmsBrandMapper.selectByExample(new PmsBrandExample());
  }
  
  @Override
  public PmsBrand getbrand(Long id) {
    return  this.pmsBrandMapper.selectByPrimaryKey(id);
  }
  
  
}
 

