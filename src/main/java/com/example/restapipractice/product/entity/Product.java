package com.example.restapipractice.product.entity;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import com.example.restapipractice.admin.entity.Admin;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    public LocalDateTime updatedAt;

    //생성자
    public Product(Admin admin, String name, int price){
        this.admin = admin;
        this.name = name;
        this.price = price;
    }
    //수정일 생성자
    public Product(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    //jpa생성자
    protected Product(){}

    //기능

    public Long getId() {return id;}

    public String getName() {return name;}

    public int getPrice() {return price;}

    public Admin getAdmin() {return admin;}
    //관리자이름 업데이트 메서드 & 수정일 최신화
    public Product updateAdmin(Admin admin) {
        this.admin = admin;
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
