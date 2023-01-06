package com.hjk.hjkbookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name="tag")
public class TagSQL {
    @Id
    @Column(name = "tag_id")
    private int tagID;

    @Column(name = "tag_name")
    private String tagName;

    @JsonManagedReference
    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name="tag", referencedColumnName = "tag_id")
    private List<BookDetail> bookList;
}
