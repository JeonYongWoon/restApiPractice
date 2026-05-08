package com.example.restapipractice.product.dto;

import com.example.restapipractice.admin.entity.Admin;

import java.time.LocalDateTime;

public class ProductUpdateResponseDto {
    private Admin admin;
    private String name;
    private int price;
    private LocalDateTime updatedAt;

    public ProductUpdateResponseDto(Admin admin, String name,int price, LocalDateTime updatedAt) {
        this.admin = admin;
        this.name = name;
        this.price = price;
        this.updatedAt = updatedAt;
    }

    public Admin getAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}


