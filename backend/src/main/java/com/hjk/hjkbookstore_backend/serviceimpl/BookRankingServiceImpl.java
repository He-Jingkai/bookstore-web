package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.dao.BookBriefDao;
import com.hjk.hjkbookstore_backend.dao.BookRankingDao;
import com.hjk.hjkbookstore_backend.dao.OrderItemDao;
import com.hjk.hjkbookstore_backend.entity.BookRanking;
import com.hjk.hjkbookstore_backend.entity.OrderItem;
import com.hjk.hjkbookstore_backend.service.BookRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookRankingServiceImpl implements BookRankingService {
    @Autowired
    private BookRankingDao bookRankingDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private BookBriefDao bookBriefDao;

    @Override
    public List<BookRanking> getAll(){
        List<BookRanking> bookRankings=bookRankingDao.getAll();
        for (BookRanking bookRanking : bookRankings) {
            //            List<OrderItem> orderItems=orderItemDao.findOrderItemsByBook_Id(bookRanking.getId());
            List<OrderItem> orderItems = bookBriefDao.findOne(bookRanking.getId()).getOrderItems();
            int total = 0;
            for (OrderItem orderItem : orderItems) {
                total += orderItem.getNum();
            }
            Integer totalPay = total * bookRanking.getPrice();

            bookRanking.setTotal(total);
            bookRanking.setTotalPay(totalPay);
        }

        return bookRankings;
    }

    @Override
    public List<BookRanking> getAllInAPeriodFromNow(Integer period){
        List<BookRanking> bookRankings=bookRankingDao.getAll();
        for (BookRanking bookRanking : bookRankings) {
            List<OrderItem> orderItems = new ArrayList<>();
            for (int i = 0; i < period; i++) {
                Calendar calendar1 = Calendar.getInstance();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");
                calendar1.add(Calendar.DATE, (-i));
                String date = sdf1.format(calendar1.getTime());

                List<OrderItem> orderItems1 = orderItemDao.findOrderItemsByBook_IdAndOrder_DateT_Date(bookRanking.getId(), date);
                orderItems.addAll(orderItems1);
            }

            int total = 0;

            for (OrderItem orderItem : orderItems) {
                total += orderItem.getNum();
            }
            Integer totalPay = total * bookRanking.getPrice();

            bookRanking.setTotal(total);
            bookRanking.setTotalPay(totalPay);
        }

        return bookRankings;
    }

    @Override
    public List<BookRanking> getAllInAPeriod(String begin,String end) throws ParseException {
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


        List<BookRanking> bookRankings=bookRankingDao.getAll();

        for (BookRanking bookRanking : bookRankings) {
            List<OrderItem> orderItems = new ArrayList<>();
            for (String date : days) {
                List<OrderItem> orderItems1 = orderItemDao.findOrderItemsByBook_IdAndOrder_DateT_Date(bookRanking.getId(), date);
                orderItems.addAll(orderItems1);
            }

            int total = 0;
            for (OrderItem orderItem : orderItems) {
                total += orderItem.getNum();
            }
            Integer totalPay = total * bookRanking.getPrice();

            bookRanking.setTotal(total);
            bookRanking.setTotalPay(totalPay);
        }

        return bookRankings;
    }
}
