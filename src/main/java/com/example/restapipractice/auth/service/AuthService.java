package com.example.restapipractice.auth.service;

import com.example.restapipractice.admin.entity.Admin;
import com.example.restapipractice.admin.repository.AdminRepository;
import com.example.restapipractice.auth.dto.CreateSignupRequestDto;
import com.example.restapipractice.auth.dto.CreateSignupResponseDto;
import com.example.restapipractice.auth.dto.LoginRequestDto;
import com.example.restapipractice.config.PasswordEncoder;
import jakarta.servlet.http.HttpSession;
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

    /**
     * 관리자 로그인 서비스
     */
    @Transactional
    // 컨트롤러에서 요청데이터 가져오기
    public void createLoginService(LoginRequestDto requestDto, HttpSession session) {
        // 2. 요청 데이터를 뽑기
        String foundEmail = requestDto.getEmail();
        String foundPassword = requestDto.getPassword();
        // 3. 요청데이터의 이메일과 디비에 이메일중 일치하는것이 있는지 확인하고 그 엔티티 조회
        Admin foundAdmin = adminRepository.findByEmail(foundEmail)
                .orElseThrow(() -> new IllegalArgumentException("이메일 혹은 비밀번호가 일치하지 않습니다."));
        // 4. 조회한 엔티티의 비밀번호데이터 뽑기
        String encodedPassword = foundAdmin.getPassword();
        // 5. 비밀번호가 디비에 암호화되있는암호와 일치하는지 확인
        boolean matches = passwordEncoder.matches(foundPassword, encodedPassword);
        // 6. 불일치시 예외처리
        if (!matches) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
        // 7. 세션을 부여해주기
        session.setAttribute("LOGIN_ADMIN_ID", foundAdmin.getId());
    }
}
