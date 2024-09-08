package com.grepp.domain.orders.controller.dto;

import com.grepp.domain.orders.Orders;
import com.grepp.domain.products.Products;
import lombok.Getter;

@Getter
public class CartResponse {

    private Long seq;
    private Products products;
    private String category;
    private Long price;
    private Integer quantity;

    public CartResponse(CartRequest request) {
        this.seq = request.getSeq();
        this.products = request.getProducts();
        this.category = request.getCategory();
        this.price = request.getPrice();
        this.quantity = request.getQuantity();
    }
}
