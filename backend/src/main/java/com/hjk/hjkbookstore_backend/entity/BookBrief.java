package com.hjk.hjkbookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="book")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BookBrief {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "inventory")
    private Integer inventory;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="book_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"book","user"})
    private List<CartItem> cartItems;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="book_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"book","order"})
    private List<OrderItem> orderItems;
}
