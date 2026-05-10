package com.example.restapipractice.auth.controller;

import com.example.restapipractice.auth.dto.CreateSignupRequestDto;
import com.example.restapipractice.auth.dto.CreateSignupResponseDto;
import com.example.restapipractice.auth.service.AuthService;
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

}
