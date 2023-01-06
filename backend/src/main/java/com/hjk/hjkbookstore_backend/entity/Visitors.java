package com.hjk.hjkbookstore_backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="visitors")
@Data
@NoArgsConstructor
public class Visitors {
    @Id
    @Column(name = "primary_key")
    private int id;

    @Column(name = "visitors")
    private Integer visitors;
}
