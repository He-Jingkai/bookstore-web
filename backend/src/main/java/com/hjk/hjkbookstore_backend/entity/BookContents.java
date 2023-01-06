package com.hjk.hjkbookstore_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ebook_mongoDB")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookContents {
    @Id
    public int id;
    public String contents;
}
