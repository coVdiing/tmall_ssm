package com.vi.tmall.service.impl;

import com.vi.tmall.mapper.OrderItemMapper;
import com.vi.tmall.pojo.Order;
import com.vi.tmall.pojo.OrderItem;
import com.vi.tmall.pojo.OrderItemExample;
import com.vi.tmall.pojo.Product;
import com.vi.tmall.service.OrderItemService;
import com.vi.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;

    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKey(orderItem);
    }

    public OrderItem get(int id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }

    public List<OrderItem> list() {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(orderItemExample);
    }

    /**
     * 创建订单和订单项的一对多关系，并给订单项中设置对应的product对象
     * @param order
     */
    public void fill(Order order) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.createCriteria().andOidEqualTo(order.getId());
        orderItemExample.setOrderByClause("id desc");
        List<OrderItem> ois = orderItemMapper.selectByExample(orderItemExample);
        //给订单项设置对应的product对象
        setProduct(ois);
        float total = 0;    //总价
        int totalNumber = 0;//商品总数量
        for (OrderItem oi : ois) {
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber+=oi.getNumber();
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(ois);
    }

    public void fill(List<Order> os) {
        for(Order order : os)
            fill(order);
    }
    public void setProduct(List<OrderItem> ois) {
        for (OrderItem oi : ois) {
            setProduct(oi);
        }
    }

    public void setProduct(OrderItem oi) {
        Product product = productService.get(oi.getPid());
        oi.setProduct(product);
    }

}
