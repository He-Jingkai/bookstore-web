package com.hjk.hjkbookstore_backend.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hjk.hjkbookstore_backend.dao.*;
import com.hjk.hjkbookstore_backend.entity.*;
import com.hjk.hjkbookstore_backend.service.CartItemService;
import com.hjk.hjkbookstore_backend.service.TempClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;


@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemDao cartItemDao;
    @Autowired
    private BookBriefDao bookBriefDao;
    @Autowired
    private UserDao userDao;
//    @Autowired
//    private DateTDao dateTDao;
//    @Autowired
//    private OrderDao orderDao;
    @Autowired
    private BookDetailDao bookDetailDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private TempClass tempClass;

    @Override
    public List<CartItem> findCartItemsByUser_Id(Integer userId){return cartItemDao.findCartItemsByUser_Id(userId);}

    @Override
    public void saveAnItem(CartItem cartItem){cartItemDao.saveAnItem(cartItem);}

    @Override
    public void deleteAnItem(CartItem cartItem){cartItemDao.deleteAnItem(cartItem);}

    @Override
    public CartItem findCartItemByItemId(Integer itemId){return cartItemDao.findCartItemByItemId(itemId);}

    @Override
    public String putOrder(String bookid, String userid) {
//        System.out.println("UserId= "+userid+" put an order");
        BookBrief bookBrief=bookBriefDao.findOne(Integer.valueOf(bookid));
        User user=userDao.findUserById(Integer.valueOf(userid));

        List<CartItem> cartItems=cartItemDao.findCartItemsByUser_Id(Integer.valueOf(userid));
        for (CartItem cartItem:cartItems){
            if (cartItem.getBook().getId()==Integer.parseInt(bookid))
                return JSON.toJSONString("ALREADY IN", SerializerFeature.BrowserCompatible);
        }

        CartItem cartItem=new CartItem();
        cartItem.setBook(bookBrief);
        cartItem.setNum(1);
        cartItem.setUser(user);
        cartItemDao.saveAnItem(cartItem);

        BookDetail bookDetail=bookDetailDao.findOne(Integer.valueOf(bookid));
        int inventory=bookDetail.getInventory()-1;
        if (inventory<0)
            return JSON.toJSONString("inventory_empty", SerializerFeature.BrowserCompatible);
        bookDetail.setInventory(inventory);
        bookDetailDao.saveOne(bookDetail,1);

        return JSON.toJSONString("INSERT", SerializerFeature.BrowserCompatible);
    }

    @Override
    public void deleteOrder(String itemId){
        CartItem cartItem=cartItemDao.findCartItemByItemId(Integer.valueOf(itemId));
        Integer previousNum=cartItem.getNum();
        cartItemDao.deleteAnItem(cartItem);
        BookDetail bookDetail=bookDetailDao.findOne(cartItem.getBook().getId());
        Integer inventory=bookDetail.getInventory()+previousNum;
        bookDetail.setInventory(inventory);
        bookDetailDao.saveOne(bookDetail,1);
    }

    @Override
    public String updateOrder(String itemId, String number) {
        CartItem cartItem=cartItemDao.findCartItemByItemId(Integer.valueOf(itemId));
        Integer previousNum=cartItem.getNum();

        BookDetail bookDetail=bookDetailDao.findOne(cartItem.getBook().getId());
        int inventory=bookDetail.getInventory()+previousNum-Integer.parseInt(number);

        if(inventory<0)
            return JSON.toJSONString("inventory_empty", SerializerFeature.BrowserCompatible);
        else {
            bookDetail.setInventory(inventory);
            bookDetailDao.saveOne(bookDetail,1);
            cartItem.setNum(Integer.parseInt(number));
            cartItemDao.saveAnItem(cartItem);
            return JSON.toJSONString("success", SerializerFeature.BrowserCompatible);
        }
    }

    @Override
    @JmsListener(destination = "orderMessageQueue")
    @Transactional(propagation = Propagation.REQUIRED)
    public void payAllOrder(Integer userid) {
        String transactionName= TransactionSynchronizationManager.getCurrentTransactionName();
        System.out.println("Function payAllOrder print: current transaction's name is: "+transactionName);

        DateT dateTToUse=tempClass.insertANewTimeStamp();
        Order order=tempClass.saveAnOrder(userid, dateTToUse);
        List<CartItem> cartItems=cartItemDao.findCartItemsByUser_Id(userid);

        for(CartItem cartItem:cartItems){
            OrderItem orderItem=new OrderItem();
            orderItem.setNum(cartItem.getNum());
            orderItem.setBook(cartItem.getBook());
            orderItem.setOrder(order);
            orderItemDao.save(orderItem);
            cartItemDao.deleteAnItem(cartItem);
        }
    }
}

