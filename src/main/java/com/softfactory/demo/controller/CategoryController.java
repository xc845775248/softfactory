package com.softfactory.demo.controller;

import com.softfactory.core.util.Pager;
import com.softfactory.demo.service.CategoryService;
import com.softfactory.pojo.Categories;
import com.softfactory.pojo.Dept;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CategoryController {
  @Resource(name = "categoryService")
  private CategoryService categoryService;

  @RequestMapping(value = "categoryController", produces = "text/html;charset=UTF-8")
  @ResponseBody
  public String list(@RequestParam(required = true, value = "page") Integer page,
                     @RequestParam(required = true, value = "rows") Integer rows,
                     @RequestParam(required = true, value = "sort") String sort,
                     @RequestParam(required = true, value = "order") String order,
                     @RequestParam(required = false, value = "categoryName") String categoryName,
                     @RequestParam(required = false, value = "categoryDescn") String categoryDescn) {

    if (!StringUtils.isEmpty(categoryName)) {
      // tips:为了体验转换为大写 (若模糊查询中文则不需要)
      categoryName = categoryName.toUpperCase();
    }
    if (!StringUtils.isEmpty(categoryDescn)) {
      categoryDescn = "%" + categoryDescn.toUpperCase() + "%";
    }

    Integer pageno = (page - 1) * rows;
    Integer pagesize = page * rows;

    Pager<Categories> pager = categoryService.findPager(pageno, pagesize, sort, order, categoryName, categoryDescn);

    JsonConfig jsonConfig = new JsonConfig();
    JSONObject json = (JSONObject) JSONSerializer.toJSON(pager, jsonConfig);

    return json.toString();
  }

  @RequestMapping(value = "categoryController_remove", produces = "text/html;charset=UTF-8")
  @ResponseBody
  public String remove(@RequestParam(required = true, value = "ids") String ids) {
    int count = 0;
    String[] categories = ids.split(",");
    for (int i = 0; i < categories.length; i++) {
      Integer categoryId = NumberUtils.createInteger(categories[i]);
      count += categoryService.delete(categoryId);
    }

    return String.valueOf(count);
  }

  @RequestMapping(value = "categoryController_save", produces = "text/html;charset=UTF-8")
  @ResponseBody
  public String save(Categories categories) {
    int count = 0;
    if (categories != null && categories.getCategoryId() != null) {
      count = categoryService.update(categories);
    } else {
      count = categoryService.add(categories);
    }

    return String.valueOf(count);
  }

  @RequestMapping(value = "categoryController_find", produces = "text/html;charset=UTF-8")
  @ResponseBody
  public String find() {
    List<Categories> categories = categoryService.find();
    Categories categorie = new Categories();
    categorie.setCategoryId(0);
    categorie.setCategoryName("请选择");
    categorie.setCategoryDescn("");
    categories.add(0, categorie);

    JsonConfig jsonConfig = new JsonConfig();
    // 设置指定属性不在 json 格式数据中显示
    jsonConfig.setExcludes(new String[]{"categoryDescn"});
    JSON json = JSONSerializer.toJSON(categories, jsonConfig);

    return json.toString();
  }

}
