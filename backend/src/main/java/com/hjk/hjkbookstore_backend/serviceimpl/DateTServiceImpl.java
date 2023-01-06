package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.dao.DateTDao;
import com.hjk.hjkbookstore_backend.entity.DateT;
import com.hjk.hjkbookstore_backend.service.DateTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateTServiceImpl implements DateTService {
    @Autowired
    private DateTDao dateTDao;

    @Override
    public void save(DateT dateT){dateTDao.save(dateT);}

    @Override
    public List<DateT> findOne(String date){return dateTDao.findOne(date);}
}
