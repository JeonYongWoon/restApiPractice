package com.example.restapipractice.product.entity;

import com.example.restapipractice.admin.entity.Admin;
import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Product {
    //속성
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    private String name;
    private int price;

    //생성자
    public Product(Admin admin, String name, int price){
        this.admin = admin;
        this.name = name;
        this.price = price;
    }

    //jpa생성자
    protected Product(){}

    //기능

    public Long getId() {return id;}

    public String getName() {return name;}

    public int getPrice() {return price;}

    public Admin getAdmin() {return admin;}
}
