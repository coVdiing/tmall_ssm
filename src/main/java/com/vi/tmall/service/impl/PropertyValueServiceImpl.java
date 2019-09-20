package com.vi.tmall.service.impl;

import com.vi.tmall.mapper.PropertyValueMapper;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.Property;
import com.vi.tmall.pojo.PropertyValue;
import com.vi.tmall.pojo.PropertyValueExample;
import com.vi.tmall.service.ProductService;
import com.vi.tmall.service.PropertyService;
import com.vi.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;
    @Autowired
    ProductService productService;

    /**
     * 初始化的目的:初始化之后，数据库中的propertyValue会得到ptid,pid,id三列数据
     * 本来propertyValue表是空的，通过这一步可以将对应的product的属性相关的propertyValue插入到表中
     * @param p
     */
    @Override
    public void init(Product p) {
        //查出p的所有属性
        List<Property> pts = propertyService.list(p.getCid());
        //获取属性值，如果为空就新建一个,插入到数据库中
        for (Property property : pts) {
            PropertyValue pv = get(property.getId(), p.getId());
            if (pv == null) {
                pv = new PropertyValue();
                pv.setPid(p.getId());
                pv.setPtid(property.getId());
                propertyValueMapper.insert(pv);
            }
        }
    }

    @Override
    public void update(PropertyValue pv) {
        propertyValueMapper.updateByPrimaryKeySelective(pv);
    }

    @Override
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if (pvs.isEmpty())
            return null;
        PropertyValue pv = pvs.get(0);
        pv.setProperty(propertyService.get(ptid));
        return pv;
    }

    /**
     * 这一步的目的是将数据库propertyValue中的数据注入到对象，
     * 在这个过程中还要将propertyValue对应的property对象设置上，这样在editPropertyValue.jsp页面
     * 我们就可以通过el表达式来进行取值
     * @param pid
     * @return
     */
    @Override
    public List<PropertyValue> list(int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        for(PropertyValue pv : pvs) {
            Property property = propertyService.get(pv.getPtid());
            pv.setProperty(property);
        }
        return pvs;
    }
}
