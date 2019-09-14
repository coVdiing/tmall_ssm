package com.vi.tmall.service.impl;

import com.vi.tmall.mapper.CategoryMapper;
import com.vi.tmall.pojo.Category;
import com.vi.tmall.pojo.CategoryExample;
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
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("id desc");
        //传递一个example对象，这个对象指定按照id倒排序来查询
        return categoryMapper.selectByExample(example);
    }


    public void add(Category category) {

        categoryMapper.insert(category);
    }

    public void delete(int id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category get(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}
