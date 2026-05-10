package com.example.restapipractice.product.service;

import com.example.restapipractice.admin.entity.Admin;
import com.example.restapipractice.admin.repository.AdminRepository;
import com.example.restapipractice.product.dto.*;
import com.example.restapipractice.product.entity.Product;
import com.example.restapipractice.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

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
    public ProductCreateResponseDto productCreateService(ProductCreateRequestDto requestDto) {
        //가져온 dto에서 데이터 추출하기
        String newName = requestDto.getName();
        int newPrice = requestDto.getPrice();
        Long adminId = requestDto.getAdminId();
        //관리자 번호가 디비에 있는지 확인 후 이름 지어주기
        Admin newAdmin = adminRepository.findById(adminId).orElseThrow(
                () -> new IllegalArgumentException("관리자id가 조회되지 않습니다")
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

    /**
     * 상품 전체조회 기능
     */
    @Transactional(readOnly = true)
    public ProductReadAllResponseDto productReadAllService() {
        // 1. 상품레포지토리의 모든 값을 조회하고 이름붙여주기
        List<Product> productInfoAll = productRepository.findAll();

        //1-1 productDtoList 만들어주기
        ArrayList<ProductDto> productDtoList = new ArrayList<>();
        for (Product newProduct : productInfoAll) {
            // 2. 디티오에 담아줄값 꺼내주기
            //2-1. 트라이캐치로 삭제된 관리자는 에러 안나오고 넘어가게 세팅하기
            try {
                String newProductName = newProduct.getName();
                int newProductPrice = newProduct.getPrice();
                Admin newProductadmin = newProduct.getAdmin();
                String adminName = newProductadmin.getName();
                // 3. 내부 dto 만들기
                ProductDto productDto = new ProductDto(newProductName, newProductPrice, adminName);
                // 4. 내부 dto list로 모아주기
                productDtoList.add(productDto);
            } catch (Exception e) {
            }

        }

        // 5. 내부 dto를 반환 디티오에 담아주기
        ProductReadAllResponseDto responseDto = new ProductReadAllResponseDto(productDtoList);

        //6.반환하기
        return responseDto;

    }

    /**
     * 상품 단건조회 서비스
     * 모니터 상품을 조회하면 해당 상품을 등록한 관리자의 이름도 함께 응답으로 반환되어야합니다.
     */
    @Transactional(readOnly = true)
    // 1.요청 매개변수 담아주기
    public ProductReadResponseDto productReadService(Long id) {
        // 2. 레포지토리안에 변수값 있는지 조회하기
        Product newProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품번호가 조회되지 않습니다."));

        // 3-0. 찾은 엔티티 안에 필요한 데이터 꺼내기(어드민만 먼저)
        Admin findAdmin = newProduct.getAdmin();
        // 3-1. 관리자 삭제되었다면 에러응답 반환
        if (findAdmin == null) {
            throw new IllegalArgumentException("담당 관리자가 삭제된 상품입니다.");
        }

        // 3. 찾은 엔티티 안에 필요한 데이터 꺼내기
        Long findID = newProduct.getId();
        String findName = newProduct.getName();
        int findPrice = newProduct.getPrice();
        String findAdminName = newProduct.getAdmin().getName();


        //4. 꺼낸 데이터 응답dto안에 넣어주기
        ProductReadResponseDto responseDto = new ProductReadResponseDto(findID, findName, findPrice, findAdminName);

        // 5. 반환하기
        return responseDto;
    }

    /**
     * 상품 수정 서비스
     * 모니터 상품의 담당관리자를 gygim 에서 steve 로 변경해보세요.
     */
    @Transactional
    // 1. 클라이언트에게서 요청 데이터 받아오기
    public ProductUpdateResponseDto productUpdateService(Long id, ProductUpdateRequestDto productUpdateRequestDto) {
        // 2. 수정할 대상 레포지토리에 있는지 확인 받기
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품 번호가 조회되지 않습니다."));
        // 3. 요청 dto에서 필요한 데이터 추출하기
        Long requestAdminId = productUpdateRequestDto.getAdmin().getId();
        // 4. 업데이트 될 데이터 레포지토리에 있는지 확인받기
        Admin newAdminId = adminRepository.findById(requestAdminId)
                .orElseThrow(() -> new IllegalArgumentException("수정될 담당관리자가 조회되 않습니다."));
        // 5. 데이터 수정하기
        Product updatedAdmin = foundProduct.updateAdmin(newAdminId);
        //6. 응답 dto 값 넣어주기
        String newAdminName = foundProduct.getAdmin().getName();
        String newName = foundProduct.getName();
        int newPrice = foundProduct.getPrice();
        LocalDateTime updatedAt = foundProduct.getUpdatedAt();

        // 6. 응답dto 만들어주기
        ProductUpdateResponseDto responseDto = new ProductUpdateResponseDto(newAdminName, newName, newPrice, updatedAt);
        // 7. 반환하기
        return responseDto;
    }


}
