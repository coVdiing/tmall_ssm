package com.vi.tmall.service.impl;

import com.vi.tmall.mapper.ProductMapper;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.pojo.ProductExample;
import com.vi.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public Product get(int id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> list(int cid) {
        //通过Category id来查找
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        return productMapper.selectByExample(example);
    }
}
