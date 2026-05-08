package com.example.restapipractice.product.dto;

public class ProductReadResponseDto {
    //속성
    public Long id;
    public String name;
    public int price;
    public String adminName;


    //기능
    public Long getId() {
        return id;
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
    //생성자
    public ProductReadResponseDto(Long id, String name, int price, String adminName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.adminName = adminName;

    }
}
