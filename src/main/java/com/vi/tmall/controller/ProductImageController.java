package com.vi.tmall.controller;

import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.ProductImage;
import com.vi.tmall.service.CategoryService;
import com.vi.tmall.service.ProductImageService;
import com.vi.tmall.service.ProductService;
import com.vi.tmall.util.ImageUtil;
import com.vi.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    //展示数据
    @RequestMapping("admin_productImage_list")
    public String list(int pid, Model model) {
        Product product = productService.get(pid);
        List<ProductImage> pisSingle = productImageService.list(pid,ProductImageService.type_single);
        List<ProductImage> pisDetail = productImageService.list(pid,ProductImageService.type_detail);
        Category category = categoryService.get(product.getCid());
        product.setCategory(category);
        //将上面这些数据获取到以后，用request传递到jsp页面展示
        model.addAttribute("product",product);
        model.addAttribute("pisSingle",pisSingle);
        model.addAttribute("pisDetail",pisDetail);

        return "admin/listProductImage";
    }

    //增加图片
    @RequestMapping("admin_productImage_add")
    public String add(ProductImage productImage, HttpSession session, UploadedImageFile uploadedImageFile) {
        //通过productImage对象接收pid和type的注入，通过ProductImage向数据库中插入数据
        productImageService.add(productImage);
        String fileName = productImage.getId()+".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        //如果上传的是单个图片的类型
        if(ProductImageService.type_single.equals(productImage.getType())) {
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
        } else {
            //如果上传的是详情图片类型
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
        }
        //根据路径和文件名生成对应的文件和路径
        File file = new File(imageFolder,fileName);
        file.getParentFile().mkdirs();
        try {
            //将图片中的内容写到文件中
            uploadedImageFile.getImage().transferTo(file);
            //将图片转成真正意义上的jpg文件，而非只是jpg后缀的文件
            BufferedImage img = ImageUtil.change2jpg(file);
            //用img覆盖项目路径中的file
            ImageIO.write(img,"jpg",file);

            //如果上传的是单个图片的类型，把正常大小的图片，
            // 改变大小之后，分别复制到productSingle_middle和productSingle_small目录下
            if(ProductImageService.type_single.equals(productImage.getType())) {
                File file_small = new File(imageFolder_small,fileName);
                File file_middle = new File(imageFolder_middle,fileName);
                ImageUtil.resizeImage(file,56,56,file_small);
                ImageUtil.resizeImage(file,217,190,file_middle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:admin_productImage_list?pid="+productImage.getPid();
    }

    @RequestMapping("admin_productImage_delete")
    public String delete(int id,HttpSession session){
       ProductImage productImage = productImageService.get(id);
       String fileName = productImage.getId()+".jpg";
       productImageService.delete(id);
       String imageFolder;
       String imageFolder_small = null;
       String imageFolder_middle = null;

        //如果是单个图片，删除三张
        if(productImage.getType().equals(ProductImageService.type_single)) {
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
            File imageFile = new File(imageFolder,fileName);
            File imageFile_small = new File(imageFolder_small,fileName);
            File imageFile_middle = new File(imageFolder_middle,fileName);
            imageFile.delete();
            imageFile_small.delete();
            imageFile_middle.delete();
        } else {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
            File imageFile = new File(imageFolder,fileName);
            imageFile.delete();
        }
        return "redirect:admin_productImage_list?pid="+productImage.getPid();
    }
}
