package com.vi.tmall.service.impl;

import com.vi.tmall.mapper.CategoryMapper;
import com.vi.tmall.pojo.Category;
import com.vi.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }


    public void add(Category category) {
        categoryMapper.add(category);
    }

    public void delete(int id) { categoryMapper.delete(id);}

    @Override
    public Category get(int id) {
        return categoryMapper.get(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }
}
