package com.example.restapipractice.admin.dto;

public class AdminCreateResponseDto {
    //속성
    public Long id;
    public String name;

    //생성자

    public AdminCreateResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    //기능


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
