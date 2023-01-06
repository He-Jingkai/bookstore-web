package com.hjk.hjkbookstore_backend.service;

import com.hjk.hjkbookstore_backend.entity.BookRanking;

import java.text.ParseException;
import java.util.List;

public interface BookRankingService {
    List<BookRanking> getAll();
    List<BookRanking> getAllInAPeriodFromNow(Integer period);
    List<BookRanking> getAllInAPeriod(String begin,String end)throws ParseException;


    }
