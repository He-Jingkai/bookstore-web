package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.dao.BookBriefDao;
import com.hjk.hjkbookstore_backend.entity.BookBrief;
import com.hjk.hjkbookstore_backend.service.BookBriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookBriefServiceImpl implements BookBriefService {
    @Autowired
    private BookBriefDao bookBriefDao;

    @Override
    public BookBrief findOne(Integer id){return bookBriefDao.findOne(id);}
}
