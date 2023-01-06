package com.hjk.hjkbookstore_backend.serviceimpl;

import com.hjk.hjkbookstore_backend.Dto.TagAndBooks;
import com.hjk.hjkbookstore_backend.dao.TagSQLDao;
import com.hjk.hjkbookstore_backend.entity.BookDetail;
import com.hjk.hjkbookstore_backend.entity.Tag;
import com.hjk.hjkbookstore_backend.entity.TagSQL;
import com.hjk.hjkbookstore_backend.repository.TagRepository;
import com.hjk.hjkbookstore_backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagSQLDao tagSQLDao;

    @Override
    public List<BookDetail> findBooksByTagName(String tagName){
        List<TagSQL> tagSQLs = tagSQLDao.findTagSQLByTagName(tagName);
        if(!tagSQLs.isEmpty())
            return tagSQLs.get(0).getBookList();
        return null;
    }

    @Override
    public Set<Tag> findRelatedTags(String tagName){
        List<Tag> tags = tagRepository.findByTagNameContaining(tagName);
        if(!tags.isEmpty())
            return tags.get(0).getRelatedTags();
        else
            return new HashSet<>();
    }

    @Override
    public Set<Tag> findSecondRelatedTags(String tagName){
        Set<Tag> firstRelated = findRelatedTags(tagName);
        Set<Tag> tagSet = new HashSet<>();
        for(Tag tag:firstRelated)
            tagSet.addAll(tag.getRelatedTags());
        return tagSet;
    }

    @Override
    public TagAndBooks firstAndSecondRelatedTagAndBooks(String tagName){
        List<Tag> matchedTag = tagRepository.findByTagNameContaining(tagName);
        if(matchedTag.isEmpty())
            return new TagAndBooks();
        Set<Tag> firstRelatedTags = findRelatedTags(tagName);
        Set<Tag> secondRelatedTags = findSecondRelatedTags(tagName);
        List<BookDetail> firstRelatedBooks = new ArrayList<>();
        for(Tag tag : firstRelatedTags)
            firstRelatedBooks.addAll(tagSQLDao.findTagSQLByTagName(tag.getTagName()).get(0).getBookList());
        List<BookDetail> secondRelatedBooks = new ArrayList<>();
        for(Tag tag : secondRelatedTags)
            secondRelatedBooks.addAll(tagSQLDao.findTagSQLByTagName(tag.getTagName()).get(0).getBookList());
        return new TagAndBooks(tagName, matchedTag.get(0), firstRelatedTags,secondRelatedTags,firstRelatedBooks,secondRelatedBooks);
    }

}
