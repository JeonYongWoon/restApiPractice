package com.example.restapipractice.auth.dto;

public class LoginRequestDto {
    //속성
    private String email;
    private String password;
    //생성자
    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    //기능
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
