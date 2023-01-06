package com.hjk.hjkbookstore_backend.repository;

import com.hjk.hjkbookstore_backend.entity.BookBrief;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBriefRepository extends JpaRepository<BookBrief,Integer> {
}
