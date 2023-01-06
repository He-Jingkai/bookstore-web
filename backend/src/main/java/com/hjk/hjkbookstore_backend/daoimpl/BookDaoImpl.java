package com.hjk.hjkbookstore_backend.daoimpl;

import com.alibaba.fastjson.JSONArray;
import com.hjk.hjkbookstore_backend.dao.BookDao;
import com.hjk.hjkbookstore_backend.entity.Book;
import com.hjk.hjkbookstore_backend.repository.BookRepository;
import com.hjk.hjkbookstore_backend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Book findOne(Integer id){
        Book book;
//        System.out.println("Searching Book: " + id + " in Redis");
        Object p = redisUtil.get("Book" + id);
        if (p == null) {
//            System.out.println("Book: " + id + " is not in Redis");
//            System.out.println("Searching Book: " + id + " in DB");
            book = bookRepository.getOne(id);
//            System.out.println("put Book: " + id + " to Redis");
            redisUtil.set("Book" + id, JSONArray.toJSON(book));
        } else {
            book = JSONArray.parseObject(p.toString(), Book.class);
//            System.out.println("Book: " + id + " is in Redis");
        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
//        System.out.println("************* find all books *************");
//        System.out.println("*** find all ID ***");
        List<Integer> ids=bookRepository.getAllId();
        List<Book> books=new ArrayList<>();
        for(Integer id:ids)
            books.add(findOne(id));
//        System.out.println("************* find all books end *************");
        return books;
    }

    @Override
    public List<Book> getBooksPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        List<Integer> ids=bookRepository.getAllPageId(pageRequest);
//        System.out.println("************* find all books in page"+pageNum+" *************");
        List<Book> books=new ArrayList<>();
        for(Integer id:ids)
            books.add(findOne(id));
//        System.out.println("************* find all books in page"+pageNum+" end *************");
        return books;
        //        return bookRepository.findAll(pageRequest);
    }

    @Override
    public Long count(){
        return bookRepository.count();
    }
}
