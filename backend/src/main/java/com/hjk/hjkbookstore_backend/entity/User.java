package com.hjk.hjkbookstore_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "live")
    private String live;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "administractor")
    private int administractor;

    @Column(name = "ban")
    private int ban;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"user"})
    private List<Order> orders;

    //must use LAZY
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"user"})
    private List<CartItem> cartItems;

    public User(int id, String live, String password, String username, String address, String email, String telephone) {
        this.id = id;
        this.live = live;
        this.password = password;
        this.username=username;
        this.address=address;
        this.email = email;
        this.telephone = telephone;
        this.administractor=0;
        this.ban=0;
    }
}

