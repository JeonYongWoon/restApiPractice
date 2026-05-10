package com.example.restapipractice.admin.dto;

public class AdminCreateRequestDto {
    //속성
    public String name;
    public String password;
    public  String email;

    //생성자
    public AdminCreateRequestDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public AdminCreateRequestDto(String email) {
        this.email = email;
    }

    //기능
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
