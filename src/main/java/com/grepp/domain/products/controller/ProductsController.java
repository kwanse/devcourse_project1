package com.grepp.domain.products.controller;

import com.grepp.domain.products.Products;
import com.grepp.domain.products.service.ProductsUseCase;
import com.grepp.domain.products.controller.dto.ProductsRequest;
import com.grepp.domain.products.controller.dto.ProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductsController {

    private final ProductsUseCase productsService;

    @GetMapping // 목록 조회
    public ResponseEntity<List<ProductsResponse>> getAllProducts() {

        List<ProductsResponse> responses = productsService.getProducts().stream()
                .map(ProductsResponse::new)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping // 등록
    public ResponseEntity<ProductsResponse> addProduct(@RequestBody ProductsRequest request) {
        Products products = productsService.addProduct(request);
        return ResponseEntity.ok(new ProductsResponse(products));
    }

    @GetMapping("/{productId}") // 조회
    public ResponseEntity<ProductsResponse> getProductDetails(@PathVariable("productId") UUID productId) {
        Products products = productsService.getProduct(productId);
        return ResponseEntity.ok(new ProductsResponse(products));
    }

    @PutMapping("/{productId}") // 수정
    public ResponseEntity<ProductsResponse> updateProduct(@PathVariable("productId") UUID productId
            , @RequestBody ProductsRequest request) {

        Products products = productsService.updateProduct(productId, request);
        return ResponseEntity.ok(new ProductsResponse(products));
    }

    @DeleteMapping("/{productId}") // 삭제
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") UUID productId) {

        productsService.removeProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
