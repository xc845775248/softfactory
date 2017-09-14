package com.softfactory.demo.dao;

import com.softfactory.pojo.Categories;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryMapper")
public interface CategoryMapper {
  @Insert("insert into CATEGORIES (CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCN)\n" +
          "values (#{categoryId}, #{categoryName}, #{categoryDescn,jdbcType=VARCHAR});")
  @SelectKey(statement = "select SEQ_CATEGORY.nextval from dual", keyProperty = "categoryId", resultType = int.class, before = true)
  int add(Categories categories);

  @Update("update CATEGORIES set CATEGORY_NAME=#{categoryName}, CATEGORY_DESCN=#{categoryDescn,jdbcType=VARCHAR} where CATEGORY_ID=#{categoryId} ")
  int update(Categories categories);
  @Delete("delete from CATEGORIES where CATEGORY_ID=#{categoryId}")
  int delete(Integer categoryId );

  @Select("select CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCN from CATEGORIES where CATEGORY_ID=#{categoryId}")
  Categories findById(Integer categoryId);

  @Select("select CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCN from CATEGORIES ")
  List<Categories> findAll();

  List<Categories> findPager(
          @Param("pageno") Integer pageno,
          @Param("pagesize") Integer pagesize,
          @Param("sort") String sort,
          @Param("order") String order,
          @Param("categoryName") String categoryName,
          @Param("categoryDescn") String categoryDescn);

  long findPagerTotal(@Param("categoryName") String categoryName, @Param("categoryDescn") String categoryDescn);

}
