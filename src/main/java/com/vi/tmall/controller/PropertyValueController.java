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
    public String edit(int pid, Model model) {
        Product product = productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> result = propertyValueService.list(pid);
        model.addAttribute("propertyValueList",result);
        model.addAttribute("product",product);
        return "admin/editPropertyValue";
    }

    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue propertyValue) {
        propertyValueService.update(propertyValue);
        return "success";
    }
}
