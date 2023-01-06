package com.hjk.hjkbookstore_backend.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hjk.hjkbookstore_backend.entity.Book;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from Book b")
    List<Book> getBooks();

    @Query(value = "select id FROM Book")
    List<Integer> getAllId();

    @Query("select book.id from Book book")
    List<Integer> getAllPageId(PageRequest pageRequest);

}
