package com.vi.tmall.controller;

import com.vi.tmall.pojo.Category;
import com.vi.tmall.service.impl.CategoryServiceImpl;
import com.vi.tmall.util.ImageUtil;
import com.vi.tmall.util.Page;
import com.vi.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model,Page page){
        System.out.println("进入控制器list!!");
        int total = categoryService.getTotal();
        page.setTotal(total);
        List<Category> cs = categoryService.list(page);
        model.addAttribute("cs",cs);
        model.addAttribute("page",page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        System.out.println("进入控制器!!");
        System.out.println("cid1:"+c.getId());
        categoryService.add(c);
        System.out.println("cid2:"+c.getId());
        System.out.println("cname:"+c.getName());
        File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,c.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_category_list";
    }
}
