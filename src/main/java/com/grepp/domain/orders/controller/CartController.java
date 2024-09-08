package com.grepp.domain.orders.controller;

import com.grepp.domain.orders.controller.dto.CartRequest;
import com.grepp.domain.orders.controller.dto.CartResponse;
import com.grepp.domain.orders.service.OrdersUseCase;
import com.grepp.global.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cart")
@RestController
public class CartController {

    private final OrdersUseCase ordersService;
    private final MemoryService memoryService;

    @GetMapping
    public ResponseEntity<CartResponse> getCart(@CookieValue) {
        //
    }

    @PostMapping // 장바구니 추가
    public ResponseEntity<CartResponse> addToCart(@CookieValue(name = "guestId") String guestId,
                                                  CartRequest request) {
        // request 로 OrderItems 생성
        // Cache 에 OrderItems 저장
        // return 으로 돌려준 값으로 총 금액 프론트에서 계산하도록


    }

    @PutMapping // 특정 제품 개수 수정
    public ResponseEntity<CartResponse> updateCart(CartRequest request) {
        // Cache 수정


    }

    @DeleteMapping("/{seq}") // 특정 제품 제거
    public ResponseEntity<CartResponse> removeFromCart(@PathVariable Long seq) {
        // Cache 에서 제거
    }

}
