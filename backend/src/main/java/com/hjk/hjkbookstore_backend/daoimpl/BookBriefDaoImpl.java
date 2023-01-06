package com.hjk.hjkbookstore_backend.daoimpl;

import com.hjk.hjkbookstore_backend.dao.BookBriefDao;
import com.hjk.hjkbookstore_backend.entity.BookBrief;
import com.hjk.hjkbookstore_backend.repository.BookBriefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookBriefDaoImpl implements BookBriefDao {
    @Autowired
    private BookBriefRepository bookBriefRepository;

    @Override
    public BookBrief findOne(Integer id){
        return bookBriefRepository.getOne(id);
    }
}
