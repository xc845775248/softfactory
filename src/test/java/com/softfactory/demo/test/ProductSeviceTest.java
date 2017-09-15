package com.softfactory.demo.test;

import com.softfactory.core.util.Pager;
import com.softfactory.demo.service.CategoryService;
import com.softfactory.demo.service.EmpService;
import com.softfactory.demo.service.ProductService;
import com.softfactory.pojo.Categories;
import com.softfactory.pojo.Emp;
import com.softfactory.pojo.Products;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ProductSeviceTest {
  private ProductService productService;
  private CategoryService categoryService;

  @Before
  public void init() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    productService = ctx.getBean("productService", ProductService.class);
    categoryService=ctx.getBean("categoryService",CategoryService.class);

  }

  @Test
  public void add(){
    Products products=new Products();
    Categories categories=categoryService.findById(1);

    products.setProductsNo("0014");
    products.setProductsName("DELL灵越14");
    products.setProductsPrice(4400d);
    products.setCategories(categories);
    int count=productService.add(products);
    if (count>0){
      System.out.println("添加成功");
    }else{
      System.out.println("添加失败");
    }
  }

  @Test
  public void findbyid(){
    Products products=productService.findById("0002");
    System.out.println(products.getProductsName() + " " + products.getProductsPrice());
  }

@Test
  public void findPager(){
    Integer page = 1;
    Integer rows = 5;
    String sort = "productsName";
    String order = "asc";

    // 条件查询的参数
    String productsName = null;
    // ename = "%S%";
    Integer categoryId = 0;

    // 处理记录的开始页/结束页
    Integer pageno = (page - 1) * rows;
    Integer pagesize = page * rows;

    Pager<Products> pager = productService.findPager(pageno, pagesize, sort, order, productsName, categoryId);
    System.out.println("Total Rows: " + pager.getTotal());
    for (Products products : pager.getRows()) {
      System.out.println(products.getProductsName() + " " + products.getProductsPrice());
    }  }
}
