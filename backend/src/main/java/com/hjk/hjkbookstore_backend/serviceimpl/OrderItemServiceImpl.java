package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.dao.OrderItemDao;
import com.hjk.hjkbookstore_backend.entity.OrderItem;
import com.hjk.hjkbookstore_backend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;

    @Override
    public void save(OrderItem orderItem){orderItemDao.save(orderItem);}

}
