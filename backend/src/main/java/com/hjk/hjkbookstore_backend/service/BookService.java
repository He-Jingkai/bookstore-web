package com.hjk.hjkbookstore_backend.service;

import com.hjk.hjkbookstore_backend.entity.Book;
import com.hjk.hjkbookstore_backend.Dto.SearchPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    Book findBookById(Integer id);

    List<Book> getBooks();

    SearchPage getBooksPage(Integer pageNum, Integer pageSize);

    SearchPage search(String pageNum,String needle);
}
