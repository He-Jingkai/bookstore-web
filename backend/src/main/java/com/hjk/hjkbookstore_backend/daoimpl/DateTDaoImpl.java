package com.hjk.hjkbookstore_backend.daoimpl;

import com.hjk.hjkbookstore_backend.dao.DateTDao;
import com.hjk.hjkbookstore_backend.entity.DateT;
import com.hjk.hjkbookstore_backend.repository.DateTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DateTDaoImpl implements DateTDao {
    @Autowired
    private DateTRepository dateTRepository;

    @Override
    public void save(DateT dateT){dateTRepository.save(dateT);}

    @Override
    public List<DateT> findOne(String date){return dateTRepository.findByDate(date);}

}
