package com.vi.tmall.service;

import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    void init(Product product);
    void update(PropertyValue propertyValue);
    PropertyValue get(int ptid,int pid);
    List<PropertyValue> list(int pid);
}
