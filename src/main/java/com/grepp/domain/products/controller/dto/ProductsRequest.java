package com.grepp.domain.products.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsRequest {

    private String productName;
    private String category;
    private Long price;
    private String description;
}
