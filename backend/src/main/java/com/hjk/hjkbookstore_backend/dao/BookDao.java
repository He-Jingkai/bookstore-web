package com.hjk.hjkbookstore_backend.dao;

import com.hjk.hjkbookstore_backend.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookDao {
    Book findOne(Integer id);
    List<Book> getBooks();
    List<Book> getBooksPage(Integer pageNum,Integer pageSize);
    Long count();

    }


