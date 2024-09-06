package com.grepp.domain.products.controller.dto;


import com.grepp.domain.products.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsResponse {

    private String productName;
    private String category;
    private Long price;
    private String description;

    public ProductsResponse(Products products) {
        this.productName = products.getProductName();
        this.category = products.getCategory();
        this.price = products.getPrice();
        this.description = products.getDescription();
    }
}
