package com.example.restapipractice.admin.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.type.NumericBooleanConverter;

//1. 필수 어노테이션 4개 입력 엔티티, 테이블,아이디, 제네레이트벨류
@Entity
@Table(name = "Admins")
@SoftDelete(columnName = "is_deleted", converter = NumericBooleanConverter.class)
public class Admin {
    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //이메일 중복불가 설정
    @Column(unique = true)
    private String email;
    private String name;
    private String password;

    //생성자


    public Admin(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    //jpa생성자
    protected Admin() {

    }

    //기능
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

