package com.hjk.hjkbookstore_backend.daoimpl;

import com.hjk.hjkbookstore_backend.dao.OrderItemDao;
import com.hjk.hjkbookstore_backend.entity.OrderItem;
import com.hjk.hjkbookstore_backend.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public void save(OrderItem orderItem){orderItemRepository.save(orderItem);}

    @Override
    public List<OrderItem> findOrderItemsByBook_Id(Integer bookId){return orderItemRepository.findOrderItemsByBook_Id(bookId);}

    @Override
    public List<OrderItem> findOrderItemsByBook_IdAndOrder_DateT_Date(Integer bookId,String date){return orderItemRepository.findOrderItemsByBook_IdAndOrder_DateT_Date(bookId,date);}

}
