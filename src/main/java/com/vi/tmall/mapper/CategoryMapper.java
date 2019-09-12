package com.vi.tmall.mapper;

import com.vi.tmall.pojo.Category;
import com.vi.tmall.util.Page;


import java.util.List;

public interface CategoryMapper {
    List<Category> list(Page page);
    int getTotal();
    void add(Category category);
    void delete(int id);
    Category get(int id);
}
