package com.vi.tmall.service.impl;

import com.vi.tmall.mapper.PropertyValueMapper;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.Property;
import com.vi.tmall.pojo.PropertyValue;
import com.vi.tmall.pojo.PropertyValueExample;
import com.vi.tmall.service.PropertyService;
import com.vi.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;

    public void init(Product product) {
        //根据产品获取分类id，并查找出对应的全部属性
        List<Property> list = propertyService.list(product.getCid());
        //遍历属性对象，如果是空，给他赋值。完成初始化
        for(Property property : list) {
            PropertyValue propertyValue = get(property.getId(),product.getId());
            if(propertyValue == null) {
                propertyValue = new PropertyValue();
                propertyValue.setId(property.getId());
                propertyValue.setPtid(product.getId());
                propertyValueMapper.insert(propertyValue);
            }
        }
    }

    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKey(propertyValue);
    }

    public PropertyValue get(int ptid,int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andIdEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        if(!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        for(PropertyValue propertyValue : result) {
            Property property = propertyService.get(propertyValue.getPid());
            propertyValue.setProperty(property);
        }
        return result;
    }
}
