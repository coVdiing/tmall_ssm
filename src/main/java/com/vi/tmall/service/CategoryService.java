package com.vi.tmall.service;

import com.vi.tmall.pojo.Category;
import com.vi.tmall.util.Page;

import java.util.List;

public interface CategoryService {
    List<Category> list(Page page);
    int getTotal();
    void add(Category category);
    void delete(int id);
    Category get(int id);
    void update(Category category);
}
