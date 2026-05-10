package com.example.restapipractice.auth.dto;

public class CreateSignupResponseDto {
    private final String name;
    private final String email;

    public CreateSignupResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
