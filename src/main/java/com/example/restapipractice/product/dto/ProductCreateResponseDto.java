package com.example.restapipractice.product.dto;

import com.example.restapipractice.admin.entity.Admin;

public class ProductCreateResponseDto {
    //속성
    public String adminName;
    public String name;
    public int price;
    //생성자

    public ProductCreateResponseDto(String adminName, String name, int price) {
        this.adminName = adminName;
        this.name = name;
        this.price = price;
    }

    //기능

    public String getAdminName() {
        return adminName;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
