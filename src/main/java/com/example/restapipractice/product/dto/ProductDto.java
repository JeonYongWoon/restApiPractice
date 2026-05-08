package com.example.restapipractice.product.dto;

public class ProductDto {
    public String name;
    public int price;
    public String adminName;

    public ProductDto(String name, int price, String adminName) {
        this.name = name;
        this.price = price;
        this.adminName = adminName;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getAdminName() {
        return adminName;
    }
}
