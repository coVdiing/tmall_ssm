package com.vi.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.service.ProductService;
import com.vi.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_product_list")
    public String list(int cid, Page page, Model model) {
        Category category = categoryService.get(cid);
        new PageHelper().offsetPage(page.getStart(),page.getCount());
        List<Product> productList = productService.list(cid);
        int total = (int) new PageInfo<>(productList).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+cid);
        model.addAttribute("productList",productList);
        model.addAttribute("category",category);
        model.addAttribute("page",page);
        return "admin/listProduct";
    }

    @RequestMapping("admin_product_add")
    public String add(Product product) {
        product.setCreateDate(new Date());
        productService.add(product);
        return "redirect:admin_product_list?cid="+product.getCid();
    }

    @RequestMapping("admin_product_edit")
    public String edit(int id,Model model) {
        Product product = productService.get(id);
        Category category = categoryService.get(product.getCid());
        model.addAttribute("product",product);
        model.addAttribute("category",category);
        return "admin/editProduct";
    }

    @RequestMapping("admin_product_update")
    public String update(Product product) {
        Category category = categoryService.get(product.getCid());
        product.setCategory(category);
        productService.update(product);
        return "redirect:admin_product_list?cid="+category.getId();
    }

    @RequestMapping("admin_product_delete")
    public String delete(int id) {
        Product product = productService.get(id);
        productService.delete(id);
        return "redirect:admin_product_list?cid="+product.getCid();
    }
}
