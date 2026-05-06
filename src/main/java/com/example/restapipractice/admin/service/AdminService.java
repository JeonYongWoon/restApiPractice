package com.example.restapipractice.admin.service;

import com.example.restapipractice.admin.controller.AdminController;
import com.example.restapipractice.admin.dto.AdminCreateRequestDto;
import com.example.restapipractice.admin.dto.AdminCreateResponseDto;
import com.example.restapipractice.admin.entity.Admin;
import com.example.restapipractice.admin.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {
    //속성
    // 레포지토리 선언하기
    private AdminRepository adminRepository;

    //생성자
    // 레포지토리 생성자 만들어주기
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    //기능
    @Transactional
    //1. 컨트롤러에서 데이터 받아오기
    public AdminCreateResponseDto adminCreateService(AdminCreateRequestDto requestDto){
        // 2. dto에서 내가 원했던 데이터 꺼내기
        String nameThatIWant = requestDto.getName();

        // 3. 엔티티에 담아주고 새이름 붙여주기
        Admin newAdmin = new Admin(nameThatIWant);

        //4. 레포지토리에 저장하기 & 저장한데이터 이름 붙여주기
        Admin savedAdmin = adminRepository.save(newAdmin);

        //5.저장한 데이터 중 응답dto에 넣어줄 데이터 만들기
        Long savedAdminIdid = savedAdmin.getId();
        String savedAdminName = savedAdmin.getName();

        //6. 응답 dto에 데이터 넣어주기
        AdminCreateResponseDto responseDto = new AdminCreateResponseDto(savedAdminIdid, savedAdminName);

        //6. dto 컨트롤러로 반환하기
        return responseDto;
    }
}
