package com.vi.tmall.service;

import com.vi.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    //表示单个图片
    String type_single = "type_single";
    //表示详情图片
    String type_detail = "type_detail";

    void add(ProductImage productImage);
    void delete(int id);
    void update(ProductImage productImage);
    ProductImage get(int id);
    //根据产品id和图片类型查询的list方法
    List list(int pid, String type);
}
