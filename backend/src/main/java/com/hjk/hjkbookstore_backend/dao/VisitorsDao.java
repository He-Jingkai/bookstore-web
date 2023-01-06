package com.hjk.hjkbookstore_backend.dao;

public interface VisitorsDao {
    Integer getInitialVisitorCount();

    void setVisitorCount(Integer visitorCount);
}
