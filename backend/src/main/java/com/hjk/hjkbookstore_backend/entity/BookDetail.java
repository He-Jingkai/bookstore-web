package com.hjk.hjkbookstore_backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="book")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","tagSQL"})
public class BookDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

//    @Column(name = "content")
//    private String content;
    @Transient
    private String contents;


    @Column(name = "writer")
    private String writer;

    @Column(name = "inventory")
    private Integer inventory;

    @Column(name = "isbn")
    private String isbn;

//    @JsonManagedReference
//    @ManyToOne( fetch = FetchType.EAGER)
//    @JoinColumn(name="tag", referencedColumnName = "tag_id")
//    @JsonIgnoreProperties({"bookList"})
//    private TagSQL tagSQL;
}

