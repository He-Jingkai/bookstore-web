package com.hjk.hjkbookstore_backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @JsonManagedReference
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name="date_id", referencedColumnName = "dateID")
    @JsonIgnoreProperties({"orderList"})
    private DateT dateT;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id", referencedColumnName = "order_id")
    @JsonIgnoreProperties({"order"})
    private List<OrderItem> orderItems;
}
