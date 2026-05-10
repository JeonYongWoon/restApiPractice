package com.example.restapipractice.admin.controller;

import com.example.restapipractice.admin.dto.AdminCreateRequestDto;
import com.example.restapipractice.admin.dto.AdminCreateResponseDto;
import com.example.restapipractice.admin.service.AdminService;
import com.example.restapipractice.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {
    //속성
    // 1. 3계층 구조니까 서비스 파이널로 불러오기
    private final AdminService adminService;
    private final ProductService productService;

    //생성자
    //2. 서비스 생성자 만들어주기
    public AdminController(AdminService adminService, ProductService productService){
        this.adminService=adminService;
        this.productService = productService;
    }

    //기능
    //3. 관리자 회원가입 컨트롤러로직 생성
    @PostMapping("/signup")
    //4. 클라이언트한테서 생성요청dto받아오기
    public ResponseEntity<AdminCreateResponseDto>
    adminCreateController(@RequestBody AdminCreateRequestDto requestDto) {
        //5. 받은 dto 서비스에게 넘기기 &  처리 결과 받기
        AdminCreateResponseDto responseDto = adminService.adminCreateService(requestDto);
        //6. 멋진 반환 객체 만들기
        ResponseEntity<AdminCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        // 7. 멋진 객체 반환하기
        return response;


    }

    /**
     * 관리자 삭제하기
     * > `gygim`  관리자를 삭제해봅시다. 그의 담당 상품인 `모니터` 는 어떻게 처리되어야할까요?
     * - 이 문제에서 실제로 DELETE 하지 않고 삭제여부를 표시하는 컬럼으로 관리해봅시다. - soft-delete
     * - `gygim` 이 soft-delete 된 후 모니터 상품을 조회하면 조회가 가능해야할까요?
     */

    @DeleteMapping("/{id}")
    // 1.클라이언트한테 요청 url 받기
    public ResponseEntity<Void> adminDeleteController(@PathVariable Long id) {
        // 2. 서비스한테 요청하고 결과값 받기
        adminService.adminDeleteService(id);
        // 3. 응답객체 만들기
        ResponseEntity<Void> response = ResponseEntity.noContent().build();
        // 4. 결과값 반환하기
        return response;
    }
}
