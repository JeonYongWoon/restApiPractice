package com.example.restapipractice.admin.entity;

import jakarta.persistence.*;

//1. 필수 어노테이션 4개 입력 엔티티, 테이블,아이디, 제네레이트벨류
@Entity
@Table(name = "Admins")
public class Admin {
    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //생성자
    public Admin(String name){
        this.name = name;
    }
    //jpa생성자
    protected Admin(){}

    //기능
    public String getName(){
        return name;
    }

    public Long getId() {
        return id;
    }
}
