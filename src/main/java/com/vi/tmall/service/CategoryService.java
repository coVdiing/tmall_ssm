package com.vi.tmall.service;

import com.vi.tmall.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> list();

    void add(Category category);
}
