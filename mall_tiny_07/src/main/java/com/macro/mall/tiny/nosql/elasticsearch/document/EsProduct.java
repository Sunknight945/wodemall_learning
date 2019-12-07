package com.macro.mall.tiny.nosql.elasticsearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ovo
 */


@Document(indexName = "pms", type = "product", shards = 1, replicas = 0)
public class EsProduct implements Serializable {
  
  private static final long serialVersionUID = -1L;
  
  @Id
  private Long id;
  /**
   * 货号
   */
  @Field(type = FieldType.Keyword)
  private String productSn;
  private Long brandId;
  /**
   * 品牌名称
   */
  @Field(type = FieldType.Keyword)
  private String brandName;
  private Long productCategoryId;
  /**
   * 商品分类名称
   */
  @Field(type = FieldType.Keyword)
  private String productCategoryName;
  private String pic;
  /**
   *
   */
  @Field(type = FieldType.Text, analyzer = "ik_max_word")
  private String name;
  /**
   * 副标题
   */
  @Field(type = FieldType.Text, analyzer = "ik_max_word")
  private String subTitle;
  /**
   * 关键字
   */
  @Field(type = FieldType.Text, analyzer = "ik_max_word")
  private String keywords;
  private BigDecimal price;
  /**
   * 销量
   */
  private Integer sale;
  private Integer newStatus;
  /**
   * 推荐状态；0->不推荐；1->推荐
   */
  private Integer recommandStatus;
  /**
   * 库存
   */
  private Integer stock;
  /**
   * 促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购
   */
  private Integer promotionType;
  /**
   * 排序
   */
  
  /**
   * 嵌套类型	nested
   * nested嵌套类型是object中的一个特例，可以让array类型的Object独立索引和查询。
   */
  private Integer sort;
  @Field(type = FieldType.Nested)
  private List<EsProductAttributeValue> attrValueList;
  
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getProductSn() {
    return productSn;
  }
  
  public void setProductSn(String productSn) {
    this.productSn = productSn;
  }
  
  public Long getBrandId() {
    return brandId;
  }
  
  public void setBrandId(Long brandId) {
    this.brandId = brandId;
  }
  
  public String getBrandName() {
    return brandName;
  }
  
  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }
  
  public Long getProductCategoryId() {
    return productCategoryId;
  }
  
  public void setProductCategoryId(Long productCategoryId) {
    this.productCategoryId = productCategoryId;
  }
  
  public String getProductCategoryName() {
    return productCategoryName;
  }
  
  public void setProductCategoryName(String productCategoryName) {
    this.productCategoryName = productCategoryName;
  }
  
  public String getPic() {
    return pic;
  }
  
  public void setPic(String pic) {
    this.pic = pic;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getSubTitle() {
    return subTitle;
  }
  
  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }
  
  public String getKeywords() {
    return keywords;
  }
  
  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }
  
  public BigDecimal getPrice() {
    return price;
  }
  
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  
  public Integer getSale() {
    return sale;
  }
  
  public void setSale(Integer sale) {
    this.sale = sale;
  }
  
  public Integer getNewStatus() {
    return newStatus;
  }
  
  public void setNewStatus(Integer newStatus) {
    this.newStatus = newStatus;
  }
  
  public Integer getRecommandStatus() {
    return recommandStatus;
  }
  
  public void setRecommandStatus(Integer recommandStatus) {
    this.recommandStatus = recommandStatus;
  }
  
  public Integer getStock() {
    return stock;
  }
  
  public void setStock(Integer stock) {
    this.stock = stock;
  }
  
  public Integer getPromotionType() {
    return promotionType;
  }
  
  public void setPromotionType(Integer promotionType) {
    this.promotionType = promotionType;
  }
  
  public Integer getSort() {
    return sort;
  }
  
  public void setSort(Integer sort) {
    this.sort = sort;
  }
  
  public List<EsProductAttributeValue> getAttrValueList() {
    return attrValueList;
  }
  
  public void setAttrValueList(List<EsProductAttributeValue> attrValueList) {
    this.attrValueList = attrValueList;
  }
}
 

