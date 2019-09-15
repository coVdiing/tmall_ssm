package com.vi.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.Property;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.service.PropertyService;
import com.vi.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;

    @RequestMapping("admin_property_list")
    public String list(int cid, Model model, Page page) {
        Category c = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps = propertyService.list(cid);
        int total =(int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+cid);

        model.addAttribute("ps",ps);
        model.addAttribute("c",c);
        model.addAttribute("page",page);

        return "admin/listProperty";
    }

    @RequestMapping("admin_property_add")
    public String add(Property p,int cid) {
        propertyService.add(p);
        return "redirect:admin_property_list?cid="+cid;
    }

    @RequestMapping("admin_property_edit")
    public String edit(int id,Model model) {
        Property property = propertyService.get(id);
        //根据Property对象的cid属性获取Category对象,并把其设置在Property对象的category属性上
        Category c = categoryService.get(property.getCid());
        property.setCategory(c);
        System.out.println("Cname->"+property.getCategory().getName());
        //把Property对象放在request的 "p" 属性中
        model.addAttribute("p",property);
        //服务端跳转到admin/editProperty.jsp
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property property) {
        // 借助propertyService更新这个对象到数据库
        propertyService.update(property);
        return "redirect:admin_property_list?cid="+property.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id) {
        Property property = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:admin_property_list?cid="+property.getCid();
    }
}
