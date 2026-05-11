package com.example.restapipractice.auth.controller;

import com.example.restapipractice.admin.entity.Admin;
import com.example.restapipractice.auth.dto.CreateSignupRequestDto;
import com.example.restapipractice.auth.dto.CreateSignupResponseDto;
import com.example.restapipractice.auth.dto.LoginRequestDto;
import com.example.restapipractice.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AuthController {
    //속성
    private final AuthService authService;

    //생성자
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //기능
    @PostMapping("/signup")
    // 1. 클라이언트한테 요청 dto받아오기
    public ResponseEntity<CreateSignupResponseDto> createSignupController(@RequestBody CreateSignupRequestDto requestDto) {
        // 2. 서비스한테 요청하고 결과 값 받기
        CreateSignupResponseDto responseDto = authService.createSignupService(requestDto);
        //3. 멋진 반환 객체 만들기
        ResponseEntity<CreateSignupResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        //4. 응답하기
        return response;
    }

    /**
     * 로그인 기능 구현하기
     *
     */
    @PostMapping("/login")
    // 1. 클라이언트한테 요청dto 받기
    public ResponseEntity<String> createLoginController(@RequestBody LoginRequestDto requestDto, HttpSession session) {
        // 2. 받은 요청 서비스에 요청하고 결과값 받기.
        authService.createLoginService(requestDto, session);
        // 3.멋진 반환 객체 만들기
        ResponseEntity<String> response = ResponseEntity.ok().body("로그인 되었습니다");
        // 4. 반환하기
        return response;


    }

}
