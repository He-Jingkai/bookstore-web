package com.hjk.hjkbookstore_backend.dao;

import com.hjk.hjkbookstore_backend.entity.DateT;
import java.util.List;

public interface DateTDao {
    void save(DateT dateT);
    List<DateT> findOne(String date);
}