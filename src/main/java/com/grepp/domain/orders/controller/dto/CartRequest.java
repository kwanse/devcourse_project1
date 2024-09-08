package com.grepp.domain.orders.controller.dto;

import com.grepp.domain.orders.Orders;
import com.grepp.domain.products.Products;
import lombok.Getter;

@Getter
public class CartRequest {

    private Long seq;
    private Products products;
    private String category;
    private Long price;
    private Integer quantity;
}
