package com.example.restapipractice.admin.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

//1. 필수 어노테이션 4개 입력 엔티티, 테이블,아이디, 제네레이트벨류
@Entity
@Table(name = "Admins")
@NoArgsConstructor
public class Admin {
    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //생성자
    public Admin(Long id, String name){
        this.id = id;
        this.name = name;
    }

    //기능
    public String getName(){
        return name;
    }
}
