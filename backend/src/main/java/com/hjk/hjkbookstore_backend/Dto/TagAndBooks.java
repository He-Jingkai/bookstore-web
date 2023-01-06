package com.hjk.hjkbookstore_backend.Dto;

import com.hjk.hjkbookstore_backend.entity.BookDetail;
import com.hjk.hjkbookstore_backend.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagAndBooks {
    String tanNameInput;

    Tag matchedTag;

    Set<Tag> firstRelatedTags;

    Set<Tag> secondRelatedTags;

    List<BookDetail> firstRelatedBooks;

    List<BookDetail> secondRelatedBooks;
}
