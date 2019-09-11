package com.vi.tmall.test;

import com.vi.tmall.mapper.CategoryMapper;
import com.vi.tmall.pojo.Category;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestMall {


    public static void main(String[] args) throws Exception {

        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SqlSessionFactory factory =
                new SqlSessionFactoryBuilder().build(ClassLoader.getSystemResourceAsStream("classpath:applicationContext.xml"));
        SqlSession session = factory.openSession();
        CategoryMapper cm = session.getMapper(CategoryMapper.class);
        for (int i = 5; i < 11; i++) {
            Category c = new Category();
            c.setName("测试分类" + i);

        }
    }
}

