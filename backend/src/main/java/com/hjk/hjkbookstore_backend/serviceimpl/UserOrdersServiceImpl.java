package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.Dto.BookAndNum;
import com.hjk.hjkbookstore_backend.Dto.UserPurchaseStatistics;
import com.hjk.hjkbookstore_backend.dao.OrderDao;
import com.hjk.hjkbookstore_backend.dao.UserDao;
import com.hjk.hjkbookstore_backend.dao.UserOrdersDao;
import com.hjk.hjkbookstore_backend.entity.*;
import com.hjk.hjkbookstore_backend.service.UserOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class UserOrdersServiceImpl implements UserOrdersService {
    @Autowired
    private UserOrdersDao userOrdersDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Override
    public List<UserOrders> getAll(){
        List<UserOrders> userOrders = userOrdersDao.getAll();

        for (UserOrders userOrders1 : userOrders) {
            //            List<Order> orders=orderDao.findOrdersByUser_Id(userOrders1.getId());
            List<Order> orders = userDao.findUserById(userOrders1.getId()).getOrders();
            int num = 0;
            int total = 0;
            for (Order order : orders) {
                List<OrderItem> orderItems = order.getOrderItems();
                for (OrderItem orderItem : orderItems) {
                    num += orderItem.getNum();
                    total += orderItem.getNum() * orderItem.getBook().getPrice();
                }

            }
            userOrders1.setTotalNum(num);
            userOrders1.setTotalPay(total);
        }

        return userOrdersDao.getAll();
    }

    @Override
    public List<UserOrders> getAllWeek(){
        List<UserOrders> userOrders=userOrdersDao.getAll();
        for (UserOrders userOrders1 : userOrders) {
            List<Order> orders = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                Calendar calendar1 = Calendar.getInstance();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
                calendar1.add(Calendar.DATE, (-i));
                String date = sdf1.format(calendar1.getTime());

                List<Order> orders_tmp = orderDao.findOrdersByUser_IdAndDateT_Date(userOrders1.getId(), date);
                orders.addAll(orders_tmp);
            }

            int total = 0;
            int num = 0;
            for (Order order : orders) {
                List<OrderItem> orderItems = order.getOrderItems();
                for (OrderItem orderItem : orderItems) {
                    num += orderItem.getNum();
                    total += orderItem.getNum() * orderItem.getBook().getPrice();
                }

            }
            userOrders1.setTotalNum(num);
            userOrders1.setTotalPay(total);
        }

        return userOrders;
    }

    @Override
    public List<UserOrders> getAllMonth(){
        List<UserOrders> userOrders=userOrdersDao.getAll();
        for (UserOrders userOrders1 : userOrders) {
            List<Order> orders = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                Calendar calendar1 = Calendar.getInstance();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
                calendar1.add(Calendar.DATE, (-i));
                String date = sdf1.format(calendar1.getTime());

                List<Order> orders_tmp = orderDao.findOrdersByUser_IdAndDateT_Date(userOrders1.getId(), date);
                orders.addAll(orders_tmp);
            }

            int total = 0;
            int num = 0;
            for (Order order : orders) {
                List<OrderItem> orderItems = order.getOrderItems();
                for (OrderItem orderItem : orderItems) {
                    num += orderItem.getNum();
                    total += orderItem.getNum() * orderItem.getBook().getPrice();
                }
            }
            userOrders1.setTotalNum(num);
            userOrders1.setTotalPay(total);
        }

        return userOrders;
    }

    @Override
    public List<UserOrders> getAllInAPeriod(String begin, String end) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");
        List<String> days = new ArrayList<>();

        Date dateBegin=dateFormat.parse(begin);
        Calendar calendarBegin=Calendar.getInstance();
        calendarBegin.setTime(dateBegin);

        Date dateEnd=dateFormat.parse(end);
        Calendar calendarEnd=Calendar.getInstance();
        calendarEnd.setTime(dateEnd);
        calendarEnd.add(Calendar.DATE, +1);

        while (calendarBegin.before(calendarEnd)) {
            days.add(dateFormat.format(calendarBegin.getTime()));
            calendarBegin.add(Calendar.DAY_OF_YEAR, 1);
        }

        List<UserOrders> userOrders=userOrdersDao.getAll();
        for (UserOrders userOrders1 : userOrders) {
            List<Order> orders = new ArrayList<>();
            for (String date : days) {
                List<Order> orders_tmp = orderDao.findOrdersByUser_IdAndDateT_Date(userOrders1.getId(), date);
                orders.addAll(orders_tmp);
            }

            int total = 0;
            int num = 0;
            for (Order order : orders) {
                List<OrderItem> orderItems = order.getOrderItems();
                for (OrderItem orderItem : orderItems) {
                    num += orderItem.getNum();
                    total += orderItem.getNum() * orderItem.getBook().getPrice();
                }

            }
            userOrders1.setTotalNum(num);
            userOrders1.setTotalPay(total);
        }

        return userOrders;
    }

    @Override
    public UserPurchaseStatistics getPeriodStatics(String userid, String begin, String end) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd");
        List<String> days = new ArrayList<>();

        Date dateBegin=dateFormat.parse(begin);
        Calendar calendarBegin=Calendar.getInstance();
        calendarBegin.setTime(dateBegin);

        Date dateEnd=dateFormat.parse(end);
        Calendar calendarEnd=Calendar.getInstance();
        calendarEnd.setTime(dateEnd);
        calendarEnd.add(Calendar.DATE, +1);

        while (calendarBegin.before(calendarEnd)) {
            days.add(dateFormat.format(calendarBegin.getTime()));
            calendarBegin.add(Calendar.DAY_OF_YEAR, 1);
        }

        List<Order> orders = new ArrayList<>();
        for (String date : days) {
            List<Order> orders_tmp = orderDao.findOrdersByUser_IdAndDateT_Date(Integer.valueOf(userid), date);
            orders.addAll(orders_tmp);
        }

        int total=0;
        int num=0;
        List<BookAndNum> bookAndNums=new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                boolean in=false;
                BookBrief bookBrief=orderItem.getBook();

                for (BookAndNum bookAndNum:bookAndNums)
                    if (bookAndNum.getBookBrief()==bookBrief){
                       Integer bookNum=bookAndNum.getNum()+orderItem.getNum();
                        bookAndNum.setNum(bookNum);
                        in=true;
                        break;
                    }

                if(!in){
                    BookAndNum bookAndNum1=new BookAndNum();
                    bookAndNum1.setBookBrief(orderItem.getBook());
                    bookAndNum1.setNum(orderItem.getNum());
                    bookAndNums.add(bookAndNum1);
                }

                num += orderItem.getNum();
                total += orderItem.getNum() * orderItem.getBook().getPrice();
            }

        }

        UserPurchaseStatistics userPurchaseStatistics=new UserPurchaseStatistics();
        userPurchaseStatistics.setTotalPay(total);
        userPurchaseStatistics.setBeginDate(begin);
        userPurchaseStatistics.setEndDate(end);
        userPurchaseStatistics.setBookBriefs(bookAndNums);
        userPurchaseStatistics.setTotalBook(num);

        return userPurchaseStatistics;
    }

    @Override
    public UserPurchaseStatistics getAllStatics(String userid){
        List<Order> orders = orderDao.findOrdersByUser_Id(Integer.valueOf(userid));

        int total=0;
        int num=0;
        List<BookAndNum> bookAndNums=new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                boolean in=false;
                BookBrief bookBrief=orderItem.getBook();

                for (BookAndNum bookAndNum:bookAndNums)
                    if (bookAndNum.getBookBrief()==bookBrief){
                        Integer bookNum=bookAndNum.getNum()+orderItem.getNum();
                        bookAndNum.setNum(bookNum);
                        in=true;
                        break;
                    }

                if(!in){
                    BookAndNum bookAndNum1=new BookAndNum();
                    bookAndNum1.setBookBrief(orderItem.getBook());
                    bookAndNum1.setNum(orderItem.getNum());
                    bookAndNums.add(bookAndNum1);
                }

                num += orderItem.getNum();
                total += orderItem.getNum() * orderItem.getBook().getPrice();
            }

        }

        UserPurchaseStatistics userPurchaseStatistics=new UserPurchaseStatistics();
        userPurchaseStatistics.setTotalPay(total);
        userPurchaseStatistics.setBeginDate("no");
        userPurchaseStatistics.setEndDate("no");
        userPurchaseStatistics.setBookBriefs(bookAndNums);
        userPurchaseStatistics.setTotalBook(num);

        return userPurchaseStatistics;
    }
}
