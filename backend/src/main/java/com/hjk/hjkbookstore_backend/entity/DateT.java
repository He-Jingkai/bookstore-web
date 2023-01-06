package com.hjk.hjkbookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name="datetable")
public class DateT {
    @Id
    @Column(name = "dateID")
    private int dateID;

    @Column(name = "date")
    private String date;

    @JsonManagedReference
    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(name="date_id", referencedColumnName = "dateID")
    private List<Order> orderList;
}
