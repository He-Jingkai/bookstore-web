package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.dao.DateTDao;
import com.hjk.hjkbookstore_backend.dao.OrderDao;
import com.hjk.hjkbookstore_backend.dao.UserDao;
import com.hjk.hjkbookstore_backend.entity.DateT;
import com.hjk.hjkbookstore_backend.entity.Order;
import com.hjk.hjkbookstore_backend.entity.User;
import com.hjk.hjkbookstore_backend.service.TempClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TempClassImpl implements TempClass {
    @Autowired
    private UserDao userDao;
    @Autowired
    private DateTDao dateTDao;
    @Autowired
    private OrderDao orderDao;

    /**
     * @Description: insert a new timestamp
     **/
    @Override
    public DateT insertANewTimeStamp() {
        String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        System.out.println("Function insertANewTimeStamp print: current transaction's name is: " + transactionName);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date(System.currentTimeMillis());
        String strDate = formatter.format(date);
        List<DateT> dateT = dateTDao.findOne(strDate);
        if (dateT.isEmpty()) {
            DateT dateT_new = new DateT();
            dateT_new.setDate(strDate);
            dateTDao.save(dateT_new);
        }
        return dateTDao.findOne(strDate).get(0);
    }

    /**
     * @Description: save an order
     **/
    @Override
    public Order saveAnOrder(Integer userid, DateT dateTToUse) {
        String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        System.out.println("Function saveAnOrder print: current transaction's name is: " + transactionName);
        Order order = new Order();
        User user = userDao.findUserById(userid);
        order.setUser(user);
        order.setDateT(dateTToUse);
        orderDao.saveAnOrder(order);
        return order;
    }

}
