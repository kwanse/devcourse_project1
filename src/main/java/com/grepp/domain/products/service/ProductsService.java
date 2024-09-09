package com.grepp.domain.products.service;

import com.grepp.domain.products.Products;
import com.grepp.domain.products.ProductsRepository;
import com.grepp.domain.products.controller.dto.ProductsRequest;
import com.grepp.global.exception.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.grepp.global.Const.NOT_FOUND_PRODUCTS;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductsService implements ProductsUseCase {

    private final ProductsRepository productsRepository;

    @Override
    public List<Products> getProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products getProduct(final UUID productId) {
        return productsRepository.findById(productId).
                orElseThrow(() -> new NoDataException(NOT_FOUND_PRODUCTS));
    }

    @Override
    public Products addProduct(final ProductsRequest request) {
        Products product = Products.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .build();

        return productsRepository.save(product);
    }

    @Override
    public Products updateProduct(final UUID productId, ProductsRequest request) {
        Products products = productsRepository.findById(productId)
                .orElseThrow(() -> new NoDataException(NOT_FOUND_PRODUCTS));

        products.updateByRequest(request);
        return products;
    }

    @Override
    public void removeProduct(final UUID productId) {
        productsRepository.deleteById(productId);
    }
}
