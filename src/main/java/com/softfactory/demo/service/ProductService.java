package com.softfactory.demo.service;

import com.softfactory.core.util.Pager;
import com.softfactory.demo.dao.ProductMapper;
import com.softfactory.pojo.Emp;
import com.softfactory.pojo.Products;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("productService")
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class ProductService {
  @Resource(name ="productMapper")
  private ProductMapper productMapper;

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int add(Products products){
    return  productMapper.add(products);
  }
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int update(Products products){
    return  productMapper.update(products);
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public int delete(String productsNo){
    return productMapper.delete(productsNo);
  }
  public  Products findById(String productsNo){
    return  productMapper.findById(productsNo);

  }

  public Pager<Products> findPager(Integer pageno, Integer pagesize, String sort, String order, String productsName,
                              Integer categoryId) {
    Pager<Products> pager = new Pager<Products>();
    // 设置分页数据
    pager.setRows(productMapper.findPager(pageno, pagesize, sort, order, productsName, categoryId));
    // 设置数据总数
    pager.setTotal(productMapper.findPagerTotal(productsName, categoryId));
    return pager;
  }


}
