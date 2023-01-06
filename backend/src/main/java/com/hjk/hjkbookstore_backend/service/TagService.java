package com.hjk.hjkbookstore_backend.service;

import com.hjk.hjkbookstore_backend.Dto.TagAndBooks;
import com.hjk.hjkbookstore_backend.entity.BookDetail;
import com.hjk.hjkbookstore_backend.entity.Tag;
import com.hjk.hjkbookstore_backend.entity.TagSQL;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<BookDetail> findBooksByTagName(String tagName);

    Set<Tag> findRelatedTags(String tagName);

    Set<Tag> findSecondRelatedTags(String tagName);

    TagAndBooks firstAndSecondRelatedTagAndBooks(String tagName);
}
