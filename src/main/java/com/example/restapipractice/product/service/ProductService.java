package com.example.restapipractice.product.service;

import com.example.restapipractice.admin.entity.Admin;
import com.example.restapipractice.admin.repository.AdminRepository;
import com.example.restapipractice.product.dto.ProductCreateRequestDto;
import com.example.restapipractice.product.dto.ProductCreateResponseDto;
import com.example.restapipractice.product.entity.Product;
import com.example.restapipractice.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    //속성
    private final ProductRepository productRepository;
    private final AdminRepository adminRepository;

    //생성자
    public ProductService(ProductRepository productRepository, AdminRepository adminRepository) {
        this.productRepository = productRepository;
        this.adminRepository = adminRepository;
    }

    //기능
    @Transactional
    //컨트롤러에게서 dto 가져오기
    public ProductCreateResponseDto productCreateService(ProductCreateRequestDto requestDto){
        //가져온 dto에서 데이터 추출하기
        String newName = requestDto.getName();
        int newPrice = requestDto.getPrice();
        Long adminId = requestDto.getAdminId();
        //관리자 번호가 디비에 있는지 확인 후 이름 지어주기
        Admin newAdmin = adminRepository.findById(adminId).orElseThrow(
                ()-> new IllegalArgumentException("관리자id가 조회되지 않습니다")
        );

        //추출한 데이터 엔티티에 담기
        Product newProduct = new Product(newAdmin, newName, newPrice);

        //새로만든 엔티티 저장하기
        Product savedProduct = productRepository.save(newProduct);

        //저장한 엔티티 기반으로 반환 dto담아줄 데이터 만들기
        String savedName = savedProduct.getName();
        String newAdminName = newAdmin.getName();
        int savedPrice = savedProduct.getPrice();

        //리스폰스 디티오 만들기
        ProductCreateResponseDto responseDto = new ProductCreateResponseDto(savedName, newAdminName, savedPrice);

        //반환하기
        return responseDto;
    }

}
