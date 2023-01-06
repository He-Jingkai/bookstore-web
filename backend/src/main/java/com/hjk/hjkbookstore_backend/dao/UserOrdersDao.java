package com.hjk.hjkbookstore_backend.dao;

import com.hjk.hjkbookstore_backend.entity.UserOrders;

import java.util.List;

public interface UserOrdersDao {
    List<UserOrders> getAll();
}
