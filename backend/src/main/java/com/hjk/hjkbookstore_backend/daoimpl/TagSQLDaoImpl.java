package com.hjk.hjkbookstore_backend.daoimpl;


import com.hjk.hjkbookstore_backend.dao.TagSQLDao;
import com.hjk.hjkbookstore_backend.entity.TagSQL;
import com.hjk.hjkbookstore_backend.repository.TagSQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagSQLDaoImpl implements TagSQLDao {
    @Autowired
    private TagSQLRepository tagSQLRepository;

    @Override
    public List<TagSQL> findTagSQLByTagID(Integer tagID){
        return tagSQLRepository.findTagSQLByTagID(tagID);
    }

    @Override
    public List<TagSQL> findTagSQLByTagName(String tagName){
        return tagSQLRepository.findTagSQLByTagName(tagName);
    }

    @Override
    public void save(TagSQL tagSQL){
        tagSQLRepository.save(tagSQL);
    }

}
