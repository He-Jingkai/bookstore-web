package com.hjk.hjkbookstore_backend.service;

import com.hjk.hjkbookstore_backend.entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> findCartItemsByUser_Id(Integer userId);

    void saveAnItem(CartItem cartItem);

    void deleteAnItem(CartItem cartItem);

    CartItem findCartItemByItemId(Integer itemId);

    String putOrder(String bookid, String userid);

    void deleteOrder(String itemId);

    String updateOrder(String itemId, String number);

    void payAllOrder(Integer userid);

}
