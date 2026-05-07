package com.example.restapipractice.product.controller;

import com.example.restapipractice.product.dto.ProductCreateRequestDto;
import com.example.restapipractice.product.dto.ProductCreateResponseDto;
import com.example.restapipractice.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    //속성
    private final ProductService productService;

    //생성자
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //기능
    @PostMapping
    //클라이언트에게서 요청dto 가져오기
    public ResponseEntity<ProductCreateResponseDto> productCreate(@RequestBody ProductCreateRequestDto requestDto){
        // 1. 서비스에게 dto 전달하기
        ProductCreateResponseDto responseDto = productService.productCreateService(requestDto);
        // 2. 멋진 반환 객체 만들기
        ResponseEntity<ProductCreateResponseDto> response
                = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        // 3. 리스폰스 반환하기
        return response;

    }

}
