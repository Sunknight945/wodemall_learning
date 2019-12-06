package com.macro.mall.tiny.common.api;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author ovo
 */
public class CommonPage<T> {
  private Integer pageNun;
  private Integer pageSize;
  private Integer totalPage;
  private Long total;
  private List<T> list;
  
  /*public static <T> CommonPage<T> restPage(List<T> list) {
    CommonPage<T> result = new CommonPage<T>();
    PageInfo<T> pageInfo = new PageInfo<T>(list);
    result.setTotalPage(pageInfo.getPages());
    result.setPageNun(pageInfo.getPageNum());
    result.setPageSize(pageInfo.getPageSize());
    result.setTotal(pageInfo.getTotal());
    result.setList(pageInfo.getList());
    return result;
  }*/
  
  public static <T> CommonPage<T> rest(List<T> list) {
    CommonPage<T> result = new CommonPage<T>();
    PageInfo<T> pageInfo = new PageInfo<T>(list);
    result.setTotalPage(pageInfo.getPages());
    result.setPageSize(pageInfo.getPageSize());
    result.setTotal(pageInfo.getTotal());
    result.setList(pageInfo.getList());
    result.setPageNun(pageInfo.getPageNum());
    return result;
  }
  
  public Integer getPageNun() {
    return pageNun;
  }
  
  public void setPageNun(Integer pageNun) {
    this.pageNun = pageNun;
  }
  
  public Integer getPageSize() {
    return pageSize;
  }
  
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
  
  public Integer getTotalPage() {
    return totalPage;
  }
  
  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }
  
  public Long getTotal() {
    return total;
  }
  
  public void setTotal(Long total) {
    this.total = total;
  }
  
  public List<T> getList() {
    return list;
  }
  
  public void setList(List<T> list) {
    this.list = list;
  }
}
 

