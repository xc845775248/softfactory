package com.softfactory.demo.controller;

import com.softfactory.core.util.CategoryiesValueProcessor;
import com.softfactory.core.util.DeptValueProcessor;
import com.softfactory.core.util.JsonDateValueProcessor;
import com.softfactory.core.util.Pager;
import com.softfactory.demo.service.ProductService;
import com.softfactory.pojo.Categories;
import com.softfactory.pojo.Dept;
import com.softfactory.pojo.Emp;
import com.softfactory.pojo.Products;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class ProductsController {
  @Resource(name = "productService")
  private ProductService productService;

  @RequestMapping(value = "productController", produces = "text/html;charset=UTF-8")
  @ResponseBody
  public String list(@RequestParam(required = true, value = "page") Integer page,
                     @RequestParam(required = true, value = "rows") Integer rows,
                     @RequestParam(required = true, value = "sort") String sort,
                     @RequestParam(required = true, value = "order") String order,
                     @RequestParam(required = false, value = "productsName") String productsName,
                     @RequestParam(required = false, value = "categoryId", defaultValue = "0") Integer categoryId) {

    if (!StringUtils.isEmpty(productsName)) {
      // tips:为了体验转换为大写 (若模糊查询中文则不需要)
      productsName = productsName.toUpperCase();
    }
    Integer pageno = (page - 1) * rows;
    Integer pagesize = page * rows;

    Pager<Products> pager = productService.findPager(pageno, pagesize, sort, order, productsName, categoryId);

    JsonConfig jsonConfig = new JsonConfig();
    jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
    jsonConfig.registerJsonValueProcessor(Categories.class, new CategoryiesValueProcessor());

    JSONObject json = (JSONObject) JSONSerializer.toJSON(pager, jsonConfig);

    return json.toString();
  }

  @RequestMapping(value = "productController_remove", produces = "text/html;charset=UTF-8")
  @ResponseBody
  public String remove(@RequestParam(required = true, value = "ids") String ids) {
    int count = 0;
    String[] products = ids.split(",");
    for (int i = 0; i < products.length; i++) {
      String productsNo =products[i];
      count += productService.delete(productsNo);
    }

    return String.valueOf(count);
  }

  @RequestMapping(value = "productController_save", produces = "text/html;charset=UTF-8")
  @ResponseBody
  public String save(Products products) {
    int count = 0;
    if (products != null && products.getProductsNo() != null) {
      count = productService.update(products);
    } else {
      count = productService.add(products);
    }

    return String.valueOf(count);
  }

  @RequestMapping("/productController_view")
  public String view(@RequestParam(required = true, value = "productsNo") String productsNo, ModelMap modelMap) {
    if (productsNo != null) {
      Products products = productService.findById(productsNo);
      modelMap.put("products", products);
    }
    return "productview";
  }

  /**
   * 根据雇员编号查找指定雇员
   *
   * @return
   */
  @RequestMapping("/productController_findById")
  public String findById(@RequestParam(required = false, value = "productsNo") String productsNo, ModelMap modelMap) {
    if (productsNo != null) {
      Products products = productService.findById(productsNo);
      modelMap.put("products", products);
    }
    return "productedit";
  }

}
