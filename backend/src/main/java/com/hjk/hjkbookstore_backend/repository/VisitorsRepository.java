package com.hjk.hjkbookstore_backend.repository;

import com.hjk.hjkbookstore_backend.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorsRepository extends JpaRepository<Visitors,Integer> {
    Visitors findVisitorsById(Integer id);
}
