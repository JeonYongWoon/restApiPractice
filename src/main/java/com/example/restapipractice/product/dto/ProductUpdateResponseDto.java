package com.example.restapipractice.product.dto;

import com.example.restapipractice.admin.entity.Admin;

import java.time.LocalDateTime;

public class ProductUpdateResponseDto {
    private String adminName;
    private String name;
    private int price;
    private LocalDateTime updatedAt;

    public ProductUpdateResponseDto(String adminName, String name,int price, LocalDateTime updatedAt) {
        this.adminName = adminName;
        this.name = name;
        this.price = price;
        this.updatedAt = updatedAt;
    }

    public String getAdminName() {
        return adminName;
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


