package com.grepp.domain.products.service;

import com.grepp.domain.products.Products;
import com.grepp.domain.products.controller.dto.ProductsRequest;

import java.util.List;
import java.util.UUID;

public interface ProductsUseCase {

    List<Products> getProducts();
    Products getProduct(UUID productId);
    Products addProduct(ProductsRequest request);
    Products updateProduct(UUID productId, ProductsRequest request);
    void removeProduct(UUID productId);
}
