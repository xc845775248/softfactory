package com.softfactory.demo.dao;

import com.softfactory.pojo.Products;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productMapper")
public interface ProductMapper {
  @Insert("insert into PRODUCTS(PRODUCT_NO,CATEGORY_ID,PRODUCT_NAME,PRODUCT_PRICE,PHOTO_PATH,PRODUCT_DESCN) values(" +
          "#{productsNo},#{categories.categoryId},#{productsName},#{productsPrice},#{photoPath,jdbcType=VARCHAR},#{productsDescn,jdbcType=VARCHAR})")
  //@SelectKey(statement="select SEQ_EMP.nextval from dual",keyProperty="productsNo",resultType=int.class,before=true)
  int add(Products products);
  @Update("update PRODUCTS set CATEGORY_ID=#{categories.categoryId},PRODUCT_NAME=#{productsName},PRODUCT_PRICE=#{productsPrice},PHOTO_PATH=#{photoPath,jdbcType=VARCHAR},PRODUCT_DESCN=#{productsDescn,jdbcType=VARCHAR}")
  int update(Products products);
  @Delete("delete from PRODUCTS where PRODUCT_NO=#{productsNo}")
  int delete(String productsNo);

  @Select("select PRODUCT_NO productsNo,CATEGORY_ID categoryId,PRODUCT_NAME productsName,PRODUCT_PRICE productsPrice,PHOTO_PATH photoPath,PRODUCT_DESCN productsDescn where PRODUCT_NO=#{productsNo}")
  Products findById(String productsNo);


  List<Products> findPager(
          @Param("pageno") Integer pageno,
          @Param("pagesize") Integer pagesize,
          @Param("sort") String sort,
          @Param("order") String order,
          @Param("productsName") String productsName,
          @Param("categoryId") Integer categoryId );

  long findPagerTotal(@Param("productsName") String productsName, @Param("productsNo") Integer categoryId );
}
