package com.hjk.hjkbookstore_backend.repository;

import com.hjk.hjkbookstore_backend.entity.UserOrders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserOrdersRepository extends JpaRepository<UserOrders,Integer> {
}
