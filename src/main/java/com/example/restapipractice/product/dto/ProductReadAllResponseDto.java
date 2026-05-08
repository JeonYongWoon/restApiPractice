package com.example.restapipractice.product.dto;

import java.util.ArrayList;

public class ProductReadAllResponseDto {
    public ArrayList<ProductDto> ProductList;

    public ProductReadAllResponseDto(ArrayList<ProductDto> productList) {
        ProductList = productList;
    }

    public ArrayList<ProductDto> getProductList() {
        return ProductList;
    }
}
