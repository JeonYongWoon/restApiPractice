package com.example.restapipractice.auth.service;

import com.example.restapipractice.admin.entity.Admin;
import com.example.restapipractice.admin.repository.AdminRepository;
import com.example.restapipractice.auth.dto.CreateSignupRequestDto;
import com.example.restapipractice.auth.dto.CreateSignupResponseDto;
import com.example.restapipractice.config.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 관리자 회원가입 서비스
     */
    @Transactional
    // 1. 컨트롤러 한테 리퀘스트dto 받기
    public CreateSignupResponseDto createSignupService(CreateSignupRequestDto requestDto) {
        // 2. 이메일 중복되는지 확인하기
        if (adminRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이메일이 중복됩니다.");
        }
        //3. 비밀번호 암호화 & 저장
        String encodePassword = passwordEncoder.encode(requestDto.getPassword());
        // 4. 리퀘스트에서 필요한 정보 꺼내기
        String foundEmail = requestDto.getEmail();
        String foundName = requestDto.getName();

        // 5. 정보 엔티티에 담아주기
        Admin newAdmin = new Admin(foundEmail, foundName, encodePassword);
        // 6.레포지토리에 저장하기
        Admin savedAdmin = adminRepository.save(newAdmin);
        // 7. 저장값에서 리스폰스데이터 꺼내주기
        String adminName = savedAdmin.getName();
        String email = savedAdmin.getEmail();
        // 8. 리스펀스 dto 만들어주기
        CreateSignupResponseDto responseDto = new CreateSignupResponseDto(adminName, email);
        return responseDto;
    }
}
