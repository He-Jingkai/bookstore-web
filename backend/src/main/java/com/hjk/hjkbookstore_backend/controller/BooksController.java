package com.hjk.hjkbookstore_backend.controller;

import com.hjk.hjkbookstore_backend.entity.Book;
import com.hjk.hjkbookstore_backend.Dto.SearchPage;
import com.hjk.hjkbookstore_backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BooksController {

    @Autowired
    private BookService bookService;

    @CrossOrigin
    @RequestMapping("/books")
    public List<Book> getBooks() { return bookService.getBooks(); }

    @CrossOrigin
    @RequestMapping("/bookspage")
    public SearchPage getBooksPage(@RequestParam("pageNum") String pageNum) { return bookService.getBooksPage(Integer.valueOf(pageNum),8); }

    @CrossOrigin
    @RequestMapping("/searchByTitle")
    public SearchPage search(@RequestParam("pageNum") String pageNum,@RequestParam("needle") String needle) { return bookService.search(pageNum,needle); }
}
