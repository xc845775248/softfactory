package com.softfactory.demo.service;

import com.softfactory.core.util.Pager;
import com.softfactory.demo.dao.CategoryMapper;
import com.softfactory.pojo.Categories;
import com.softfactory.pojo.Dept;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService")
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class CategoryService {
  @Resource(name="categoryMapper")
  private CategoryMapper categoryMapper;

  public Pager<Categories> findPager(Integer pageno, Integer pagesize, String sort, String order, String categoryName, String categoryDescn) {
    Pager<Categories> pager = new Pager<Categories>();
    // 设置分页数据
    pager.setRows(categoryMapper.findPager(pageno, pagesize, sort, order, categoryName, categoryDescn));
    // 设置数据总数
    pager.setTotal(categoryMapper.findPagerTotal(categoryName, categoryDescn));
    return pager;
  }
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int add(Categories categories) {
    return categoryMapper.add(categories);
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int update(Categories categories) {
    return categoryMapper.update(categories);
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int delete(Integer categoryId) {
    return categoryMapper.delete(categoryId);
  }

  public List<Categories> find() {
    return categoryMapper.findAll();
  }

}
