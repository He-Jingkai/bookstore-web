package com.hjk.hjkbookstore_backend.service;

import com.hjk.hjkbookstore_backend.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> findOrdersByUser_Id(Integer id);

    void saveAnOrder(Order order);

    List<Order> findAll();

}
