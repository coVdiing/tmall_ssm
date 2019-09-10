package com.vi.tmall.controller;

import com.vi.tmall.pojo.Category;
import com.vi.tmall.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model){
        List<Category> cs = categoryService.list();
        model.addAttribute("cs",cs);
        return "admin/listCategory";
    }
}
