package com.softfactory.core.util;

import com.softfactory.pojo.Categories;
import com.softfactory.pojo.Dept;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class CategoryiesValueProcessor implements JsonValueProcessor {

  public CategoryiesValueProcessor() {

  }

  public Object processArrayValue(Object value, JsonConfig config) {
    return process(value);
  }

  public Object processObjectValue(String key, Object value, JsonConfig config) {
    return process(value);
  }

  private Object process(Object value) {
    if (value instanceof Categories) {
      return ((Categories) value).getCategoryName();
    }
    return value == null ? "" : value.toString();
  }
}
