package com.example.restapipractice.admin.dto;

public class AdminCreateRequestDto {
    //속성
    public String name;
    public String password;

    //생성자
    public AdminCreateRequestDto(String name,String password){
        this.name = name;
        this.password = password;
    }

    //기능
    public String getName(){
        return name;
    }


}
