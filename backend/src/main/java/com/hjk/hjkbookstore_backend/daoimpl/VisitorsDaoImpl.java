package com.hjk.hjkbookstore_backend.daoimpl;


import com.hjk.hjkbookstore_backend.dao.VisitorsDao;
import com.hjk.hjkbookstore_backend.entity.Visitors;
import com.hjk.hjkbookstore_backend.repository.VisitorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class VisitorsDaoImpl implements VisitorsDao {
    @Autowired
    private VisitorsRepository visitorsRepository;

    @Override
    public Integer getInitialVisitorCount(){
        return visitorsRepository.findVisitorsById(1).getVisitors();
    }

    @Override
    public void setVisitorCount(Integer visitorCount){
        Visitors visitors=visitorsRepository.findVisitorsById(1);
        visitors.setVisitors(visitorCount);
        visitorsRepository.save(visitors);
    }
}
