package com.example.restapipractice.auth.dto;

public class CreateSignupRequestDto {
    //속성
    private String email;
    private String name;
    private String password;


    //생성자
    public CreateSignupRequestDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }


    //기능
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
