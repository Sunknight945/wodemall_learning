package com.macro.mall.tiny.common.api;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

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
  
  /**
   * 将PageHelper分页后的list转化为分页信息
   *
   * @param list
   * @param <T>
   * @return
   */
  public static <T> CommonPage<T> restPage(List<T> list) {
    CommonPage<T> result = new CommonPage<T>();
    PageInfo<T> pageInfo = new PageInfo<T>(list);
    result.setTotalPage(pageInfo.getPages());
    result.setPageSize(pageInfo.getPageSize());
    result.setTotal(pageInfo.getTotal());
    result.setList(pageInfo.getList());
    result.setPageNun(pageInfo.getPageNum());
    return result;
  }
  
  /**
   * A page is a sublist of a list of objects. It allows gain information about the position of it in the containing entire list.
   * (这一页是对象列表的子列表。它可以获取整个列表中有关它在容器中的位置的信息(分页内容)。)
   * 将SpringData分页后的list转化为分页信息
   *
   * @param pageInfo
   * @param <T>
   * @return
   */
  public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
    CommonPage<T> result = new CommonPage<T>();
    result.setTotalPage(pageInfo.getTotalPages());
    result.setPageNun(pageInfo.getNumber());
    result.setPageSize(pageInfo.getSize());
    result.setTotal(pageInfo.getTotalElements());
    result.setList(pageInfo.getContent());
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
 

