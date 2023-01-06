package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.dao.BookDao;
import com.hjk.hjkbookstore_backend.entity.Book;
import com.hjk.hjkbookstore_backend.Dto.SearchPage;
import com.hjk.hjkbookstore_backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() { return bookDao.getBooks(); }

    @Override
    public SearchPage getBooksPage(Integer pageNum, Integer pageSize){
        Long total=bookDao.count();
        SearchPage searchPage= new SearchPage();
        searchPage.setTotalPage(total.intValue()/pageSize);
        searchPage.setCurrentPage(pageNum);
        searchPage.setNeedle("");
        searchPage.setPageSize(8);
        searchPage.setTotal(total.intValue());
        searchPage.setBooks(bookDao.getBooksPage(pageNum,pageSize));
        return searchPage;
//        return bookDao.getBooksPage(pageNum,pageSize);
    }

    @Override
    public SearchPage search(String pageNum,String needle){
        List<Book> books=bookDao.getBooks();
        Integer totalPage= (int) Math.ceil((double) books.size() / 8);
        Integer currentPage=Integer.valueOf(pageNum);
        SearchPage searchPage= new SearchPage();
        searchPage.setTotalPage(totalPage);
        searchPage.setCurrentPage(currentPage);
        searchPage.setNeedle(needle.toLowerCase(Locale.ROOT));
        searchPage.setPageSize(8);

        List<Book> booksResult= new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase(Locale.ROOT).contains(needle.toLowerCase(Locale.ROOT)))
                booksResult.add(book);
        }
        searchPage.setTotal(booksResult.size());
        if(searchPage.getTotal()>=currentPage*searchPage.getPageSize())
            searchPage.setBooks(booksResult.subList((currentPage-1)*searchPage.getPageSize(),currentPage*searchPage.getPageSize()));
        else
            searchPage.setBooks(booksResult.subList((currentPage-1)*searchPage.getPageSize(),searchPage.getTotal()));
        return searchPage;
    }
}

