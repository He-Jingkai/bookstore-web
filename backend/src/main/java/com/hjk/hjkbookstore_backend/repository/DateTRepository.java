package com.hjk.hjkbookstore_backend.repository;

import com.hjk.hjkbookstore_backend.entity.DateT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateTRepository extends JpaRepository<DateT,Integer> {
    List<DateT> findByDate(String date);
}