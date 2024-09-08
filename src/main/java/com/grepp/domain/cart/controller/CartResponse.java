package com.grepp.domain.cart.controller;

import com.grepp.domain.cart.OrderItems;
import com.grepp.domain.products.Products;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CartResponse {

    private final Long seq;
    private final Products products;
    private final String category;
    private final Long price;
    private final Integer quantity;

    public CartResponse(OrderItems orderItems) {
        this.seq = orderItems.getSeq();
        this.products = orderItems.getProducts();
        this.category = orderItems.getCategory();
        this.price = orderItems.getPrice();
        this.quantity = orderItems.getQuantity();
    }
}
