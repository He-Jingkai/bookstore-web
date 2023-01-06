package com.hjk.hjkbookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UserOrders {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Transient
    private Integer totalNum;

    @Transient
    private Integer totalPay;
}
