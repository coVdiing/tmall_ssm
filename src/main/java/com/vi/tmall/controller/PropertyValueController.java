package com.vi.tmall.controller;

import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.PropertyValue;
import com.vi.tmall.service.ProductService;
import com.vi.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;

    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model,int pid) {
        Product p = productService.get(pid);
        //对p的属性进行初始化,完成这一步骤之后，propertyValue表中对应的数据不再是空
        propertyValueService.init(p);
        //取出propertyValue对象集合
        List<PropertyValue> pvs = propertyValueService.list(p.getId());
        //加入request域中
        model.addAttribute("pvs",pvs);
        model.addAttribute("p",p);
        return "admin/editPropertyValue";
    }

    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue pv) {
        propertyValueService.update(pv);
        return "success";
    }
}
