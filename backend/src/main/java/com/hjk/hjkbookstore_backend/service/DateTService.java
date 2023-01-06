package com.hjk.hjkbookstore_backend.service;

import com.hjk.hjkbookstore_backend.entity.DateT;

import java.util.List;

public interface DateTService {
    void save(DateT dateT);
    List<DateT> findOne(String date);
}
