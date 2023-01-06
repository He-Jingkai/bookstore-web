package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.dao.VisitorsDao;
import com.hjk.hjkbookstore_backend.service.VisitorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class VisitorServiceImpl implements VisitorsService {
    @Autowired
    private VisitorsDao visitorsDao;

    private final Object lock=new Object();

    @Override
    public Integer getVisitorCount(){
            return visitorsDao.getInitialVisitorCount();
    }

    @Override
    public void increaseVisitorCount(){
        synchronized (lock){
            Integer visitorCount=visitorsDao.getInitialVisitorCount();
            visitorsDao.setVisitorCount(visitorCount+1);
        }
    }
}
