package com.example.restapipractice.product.entity;

import com.example.restapipractice.admin.entity.Admin;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Products")
public class Product {
    //속성
    @Id
    @GeneratedValue(GenerationType.IDENTITY)
    private Long id;
    private Long adminId;
    private String password;
    private int price;

    //생성자
    public Product(Long id, Long adminId, String password, int price){
        this.id = id;
        this.adminId = adminId;
        this.password = password;
        this.price = price;
    }

    //jpa생성자
    protected Product(){}


}
