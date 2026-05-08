package com.example.restapipractice.product.dto;

import com.example.restapipractice.admin.entity.Admin;

public class ProductUpdateRequestDto {
    //속성
    private Admin admin;

    //생성자

    public ProductUpdateRequestDto(Admin admin) {
        this.admin = admin;
    }


    //기능

    public Admin getAdmin() {
        return admin;
    }
}
