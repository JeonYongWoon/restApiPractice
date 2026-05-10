package com.example.restapipractice.admin.service;

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
    public AdminCreateResponseDto adminCreateService(AdminCreateRequestDto requestDto) {
        // 2. dto에서 내가 원했던 데이터 꺼내기
        String newName = requestDto.getName();
        String newEmail = requestDto.getEmail();
        String newPassword = requestDto.getPassword();

        // 3. 엔티티에 담아주고 새이름 붙여주기
        Admin newAdmin = new Admin(newName, newEmail, newPassword);

        //4. 레포지토리에 저장하기 & 저장한데이터 이름 붙여주기
        Admin savedAdmin = adminRepository.save(newAdmin);

        //5.저장한 데이터 중 응답dto에 넣어줄 데이터 만들기
        Long savedAdminId = savedAdmin.getId();
        String savedAdminName = savedAdmin.getName();

        //6. 응답 dto에 데이터 넣어주기
        AdminCreateResponseDto responseDto = new AdminCreateResponseDto(savedAdminId, savedAdminName);

        //6. dto 컨트롤러로 반환하기
        return responseDto;
    }

    /**
     * 관리자 삭제 서비스
     * `gygim`  관리자를 삭제해봅시다. 그의 담당 상품인 `모니터` 는 어떻게 처리되어야할까요?
     * 이 문제에서 실제로 DELETE 하지 않고 삭제여부를 표시하는 컬럼으로 관리해봅시다. - soft-delete
     * `gygim` 이 soft-delete 된 후 모니터 상품을 조회하면 조회가 가능해야할까요?
     */
    @Transactional
    // 1. 컨트롤러한테 요청 url 받기
    public void adminDeleteService(Long id) {
        // 2. 받은 데이터가 디비에 있는지 확인하기
        Admin foundAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제 요청한 관리자가 조회되지 않습니다."));
        // 3. 관리자 삭제하기
        adminRepository.delete(foundAdmin);

    }
}
