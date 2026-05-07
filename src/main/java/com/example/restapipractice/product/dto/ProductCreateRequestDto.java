package com.example.restapipractice.product.dto;

import com.example.restapipractice.admin.entity.Admin;

public class ProductCreateRequestDto {
    //속성
    public String name;
    public int price;
    public Long adminId;

    //생성자

    public ProductCreateRequestDto(String name, int price, Long adminId) {
        this.name = name;
        this.price = price;
        this.adminId = adminId;
    }

    //기능
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Long getAdminId() {
        return adminId;
    }
}
