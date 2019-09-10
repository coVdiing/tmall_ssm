package com.vi.tmall.mapper;

import com.vi.tmall.pojo.Category;


import java.util.List;

public interface CategoryMapper {
    List<Category> list();

    void add(Category category);
}
