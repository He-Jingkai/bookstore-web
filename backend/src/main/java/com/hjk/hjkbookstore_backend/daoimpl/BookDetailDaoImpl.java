package com.hjk.hjkbookstore_backend.daoimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hjk.hjkbookstore_backend.entity.BookContents;
import com.hjk.hjkbookstore_backend.entity.BookDetail;
import com.hjk.hjkbookstore_backend.repository.BookContentsRepository;
import com.hjk.hjkbookstore_backend.repository.BookDetailRepository;
import com.hjk.hjkbookstore_backend.dao.BookDetailDao;

import com.hjk.hjkbookstore_backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class BookDetailDaoImpl implements BookDetailDao {

    @Autowired
    private BookDetailRepository bookDetailRepository;
    @Autowired
    private BookContentsRepository bookContentsRepository;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public BookDetail findOne(Integer id){
        BookDetail bookDetail;
        Object p = redisUtil.get("bookDetail" + id);
        if (p==null) {
            System.out.println("mongo");

            bookDetail = bookDetailRepository.findBookDetailById(id);
            Optional<BookContents> bookContents = bookContentsRepository.findById(id);
            if (bookContents.isPresent()){
                bookDetail.setContents(JSON.toJSONString(bookContents.get().contents, SerializerFeature.BrowserCompatible).replace("\"",""));
            }
            else{
                bookDetail.setContents("null haha");
                System.out.println("It's Null");
            }
            redisUtil.set("bookDetail" + id, JSONArray.toJSON(bookDetail));
        } else {
            bookDetail = JSONArray.parseObject(p.toString(), BookDetail.class);
        }
        return bookDetail;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BookDetail saveOne(BookDetail bookDetail,Integer mode){
        if(mode==0){
            BookDetail bookDetail1=bookDetailRepository.save(bookDetail);
            BookContents bookContents=new BookContents(bookDetail1.getId(),bookDetail1.getContents());
            bookContentsRepository.save(bookContents);
            redisUtil.set("bookDetail" + bookDetail1.getId(), JSONArray.toJSON(bookDetail1));
            return bookDetail;
        }
        else {
            Optional<BookContents> bookContents = bookContentsRepository.findById(bookDetail.getId());
            if (bookContents.isPresent()){
                BookContents bookContents1 = bookContents.get();
                bookContents1.setContents(bookDetail.getContents());
                bookContentsRepository.save(bookContents1);
            }
            redisUtil.set("bookDetail" + bookDetail.getId(), JSONArray.toJSON(bookDetail));
            return bookDetailRepository.save(bookDetail);
        }
    }

    @Override
    public List<BookDetail> findAll(){
//        System.out.println("************* find all bookDetails *************");
//        System.out.println("*** find all ID ***");
        List<Integer> ids=bookDetailRepository.getAllId();
        List<BookDetail> bookDetails=new ArrayList<>();
        for(Integer id:ids)
            bookDetails.add(findOne(id));
//        System.out.println("************* find all bookDetails end *************");
        return bookDetails;
    }

    @Override
    public void delete(BookDetail bookDetail){
//        System.out.println("delete a book");
//        System.out.println("delete bookDetail: " + bookDetail.getId() + " in Redis");
        redisUtil.del("bookDetail" + bookDetail.getId());
//        System.out.println("delete bookDetail: " + bookDetail.getId() + " in DB");
        bookDetailRepository.delete(bookDetail);
    }

}
