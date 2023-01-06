package com.hjk.hjkbookstore_backend.daoimpl;

import com.hjk.hjkbookstore_backend.dao.BookRankingDao;
import com.hjk.hjkbookstore_backend.entity.BookRanking;
import com.hjk.hjkbookstore_backend.repository.BookRankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRankingDaoImpl implements BookRankingDao {
    @Autowired
    private BookRankingRepository bookRankingRepository;

    @Override
    public List<BookRanking> getAll(){return bookRankingRepository.findAll();}
}
