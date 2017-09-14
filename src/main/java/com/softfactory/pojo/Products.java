package com.softfactory.pojo;

import java.io.Serializable;

public class Products implements Serializable{
  private static final long serialVersionUID = 1L;
  private String productsNo;
  private String productsName;
  private Double productsPrice;
  private String photoPath;
  private String productsDescn;
  private Categories categories;

  public Products() {
  }

  public Products(String productsNo, String productsName, Double productsPrice, String photoPath, String productsDescn, Categories categories) {
    this.productsNo = productsNo;
    this.productsName = productsName;
    this.productsPrice = productsPrice;
    this.photoPath = photoPath;
    this.productsDescn = productsDescn;
    this.categories = categories;
  }


  public String getProductsNo() {
    return productsNo;
  }

  public void setProductsNo(String productsNo) {
    this.productsNo = productsNo;
  }

  public String getProductsName() {
    return productsName;
  }

  public void setProductsName(String productsName) {
    this.productsName = productsName;
  }

  public Double getProductsPrice() {
    return productsPrice;
  }

  public void setProductsPrice(Double productsPrice) {
    this.productsPrice = productsPrice;
  }

  public String getPhotoPath() {
    return photoPath;
  }

  public void setPhotoPath(String photoPath) {
    this.photoPath = photoPath;
  }

  public String getProductsDescn() {
    return productsDescn;
  }

  public void setProductsDescn(String productsDescn) {
    this.productsDescn = productsDescn;
  }

  public Categories getCategories() {
    return categories;
  }

  public void setCategories(Categories categories) {
    this.categories = categories;
  }
}
