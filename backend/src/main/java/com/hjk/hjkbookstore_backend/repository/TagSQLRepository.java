package com.hjk.hjkbookstore_backend.repository;

import com.hjk.hjkbookstore_backend.entity.TagSQL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagSQLRepository extends JpaRepository<TagSQL,Integer> {
    List<TagSQL> findTagSQLByTagID(Integer tagID);

    List<TagSQL> findTagSQLByTagName(String tagName);
}
