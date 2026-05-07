package com.example.restapipractice.admin.controller;

import com.example.restapipractice.admin.dto.AdminCreateRequestDto;
import com.example.restapipractice.admin.dto.AdminCreateResponseDto;
import com.example.restapipractice.admin.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {
    //속성
    // 1. 3계층 구조니까 서비스 파이널로 불러오기
    private final AdminService adminService;

    //생성자
    //2. 서비스 생성자 만들어주기
    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

    //기능
    //3. 관리자 회원가입 컨트롤러로직 생성
    @PostMapping("/signup")
    //4. 클라이언트한테서 생성요청dto받아오기
    public ResponseEntity<AdminCreateResponseDto>
    adminCreateController(@RequestBody AdminCreateRequestDto requestDto){
        //5. 받은 dto 서비스에게 넘기기 &  처리 결과 받기
        AdminCreateResponseDto responseDto = adminService.adminCreateService(requestDto);
        //6. 멋진 반환 객체 만들기
        ResponseEntity<AdminCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        // 7. 멋진 객체 반환하기
        return response;


    }
}
