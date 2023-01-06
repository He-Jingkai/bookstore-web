package com.hjk.hjkbookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comment")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Comment {

    @Id
    @Column(name = "Cid")
    private int Cid;

    @Column(name = "id")
    private int id;

    @Column(name = "time")
    private String time;

    @Column(name = "comment")
    private String comment;

    @Column(name = "name")
    private String name;

}
