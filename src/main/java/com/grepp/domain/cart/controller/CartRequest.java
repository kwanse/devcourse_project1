package com.grepp.domain.cart.controller;

import com.grepp.domain.products.Products;
import lombok.Getter;

@Getter
public record CartRequest(Long seq, Products products, String category, Long price, Integer quantity) {

}
