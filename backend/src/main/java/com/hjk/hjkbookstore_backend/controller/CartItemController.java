package com.hjk.hjkbookstore_backend.controller;

import com.hjk.hjkbookstore_backend.entity.*;
import com.hjk.hjkbookstore_backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;


import java.util.List;

@RestController
public class CartItemController {
//    @Autowired
//    private CartItemService cartItemService;

    @Autowired
    WebApplicationContext applicationContext;

    /* Add a new cart Item */
    @CrossOrigin
    @RequestMapping("/addorder")
    public String putOrder(
            @RequestParam("bookid") String bookid ,
            @RequestParam("userid") String userid) {
        CartItemService cartItemService=applicationContext.getBean(CartItemService.class);
//        System.out.println("CartItemController address: "+this);
//        System.out.println("CartItemService address: "+cartItemService);

        return cartItemService.putOrder(bookid,userid);
    }

    @CrossOrigin
    @RequestMapping("/deleteorder")
    public String deleteOrder(@RequestParam("itemId") String itemId) {
        CartItemService cartItemService=applicationContext.getBean(CartItemService.class);
//        System.out.println("CartItemController address: "+this);
//        System.out.println("CartItemService address: "+cartItemService);
        cartItemService.deleteOrder(itemId);
        return "DONE";
    }

    @CrossOrigin
    @RequestMapping("/updateorder")
    public String updateOrder(
            @RequestParam("itemId") String itemId,
            @RequestParam("number") String number) {
        CartItemService cartItemService=applicationContext.getBean(CartItemService.class);
//        System.out.println("CartItemController address: "+this);
//        System.out.println("CartItemService address: "+cartItemService);
        return cartItemService.updateOrder(itemId,number);
    }

    @CrossOrigin
    @RequestMapping("/cartOrders")
    public List<CartItem> getCartOrders(@RequestParam("id") String id) {
        CartItemService cartItemService=applicationContext.getBean(CartItemService.class);
//        System.out.println("CartItemController address: "+this);
//        System.out.println("CartItemService address: "+cartItemService);
        return cartItemService.findCartItemsByUser_Id(Integer.valueOf(id));
    }

    @CrossOrigin
    @RequestMapping("/payall")
    public String payAllOrder(@RequestParam("userid") Integer userid) {
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend("orderMessageQueue", userid);
//        System.out.println("UserId= "+userid+" put an order to queue orderMessageQueue");

        return "DONE";
    }
}
