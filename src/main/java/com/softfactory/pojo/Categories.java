package com.softfactory.pojo;

import java.io.Serializable;

public class Categories implements Serializable{
  private static final long serialVersionUID = 1L;
  private Integer  categoryId;
  private String   categoryName;
  private String  categoryDescn;

  public Categories() {
  }

  public Categories(Integer categoryId, String categoryName, String categoryDescn) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.categoryDescn = categoryDescn;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryDescn() {
    return categoryDescn;
  }

  public void setCategoryDescn(String categoryDescn) {
    this.categoryDescn = categoryDescn;
  }
}
