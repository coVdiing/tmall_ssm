package com.vi.tmall.service;

import com.vi.tmall.pojo.Order;
import com.vi.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    void add(OrderItem orderItem);
    void delete(int id);
    void update(OrderItem orderItem);
    OrderItem get(int id);
    List<OrderItem> list();
    void fill(List<Order> os);
    void fill(Order order);
}
