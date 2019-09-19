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

    /**
     * 更新
     * @param propertyValue
     */
    public void update(PropertyValue propertyValue) {
        propertyValueMapper.updateByPrimaryKey(propertyValue);
    }

    /**
     * 单个查找
     * @param ptid
     * @param pid
     * @return
     */
    public PropertyValue get(int ptid,int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andIdEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        if(!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    /**
     * 批量查找
     * @param pid
     * @return
     */
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        //从数据库中查出propertyValue,此时拥有pid,ptid,value(可能为空)
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        for(PropertyValue propertyValue : result) {
            Property property = propertyService.get(propertyValue.getPtid());
            //给对应的propertyValue赋上我们后来添加的非数据库字段
            propertyValue.setProperty(property);
        }
        return result;
    }
}
