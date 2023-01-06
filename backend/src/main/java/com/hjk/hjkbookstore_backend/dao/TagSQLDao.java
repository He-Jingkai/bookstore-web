package com.hjk.hjkbookstore_backend.dao;

import com.hjk.hjkbookstore_backend.entity.TagSQL;

import java.util.List;

public interface TagSQLDao {
    List<TagSQL> findTagSQLByTagID(Integer tagID);

    List<TagSQL> findTagSQLByTagName(String tagName);

    void save(TagSQL tagSQL);
}
