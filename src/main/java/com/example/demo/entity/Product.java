package com.example.demo.entity;

public class Product {
  private Long productId;
  private String productCategory;
  private String productName;
  private Long productPrice;
  private Long productCount;
  private String productNew;
  private String productImg;

  public Product() {
  }

  public Product(final Long productId, final String productName, final Long productPrice, final String productCategory,
      final Long productCount, final String productNew, final String productImg) {
    this.productId = productId;
    this.productName = productName;
    this.productPrice = productPrice;
    this.productCategory = productCategory;
    this.productCount = productCount;
    this.productNew = productNew;
    this.productImg = productImg;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Long getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(Long productPrice) {
    this.productPrice = productPrice;
  }

  public String getProductCategory() {
    return productCategory;
  }

  public void setProductCategory(String productCategory) {
    this.productCategory = productCategory;
  }

  public Long getProductCount() {
    return productCount;
  }

  public void setProductCount(Long productCount) {
    this.productCount = productCount;
  }

  public String getProductNew() {
    return productNew;
  }

  public void setProductNew(String productNew) {
    this.productNew = productNew;
  }

  public String getProductImg() {
    return productImg;
  }

  public void setProductImg(String productImg) {
    this.productImg = productImg;
  }
}
