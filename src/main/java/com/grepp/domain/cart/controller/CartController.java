package com.grepp.domain.cart.controller;

import com.grepp.domain.cart.OrderItems;
import com.grepp.global.MemoryService;
import com.grepp.global.auth.CookieProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cart")
@RestController
public class CartController {

    private final MemoryService<OrderItems> memoryService;

    @GetMapping // 장바구니 전체 확인
    public ResponseEntity<List<CartResponse>> getCart(
            @CookieValue(name = "guestId", required = false) String guestId) {

        List<CartResponse> responses = memoryService.get(guestId).stream()
                .map(CartResponse::new)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping // 장바구니 추가
    public ResponseEntity<CartResponse> addToCart(@CookieValue(name = "guestId", required = false) String guestId,
                                                  CartRequest request) {
        OrderItems orderItems = memoryService.add(guestId, new OrderItems(request));
        if (guestId == null) {
            ResponseCookie cookie = CookieProvider.createCookie();
            return ResponseEntity.ok()
                    .header(cookie.toString())
                    .body(new CartResponse(orderItems));
        }
        return ResponseEntity.ok(new CartResponse(orderItems));
    }

    @PutMapping // 특정 제품 개수 수정
    public ResponseEntity<CartResponse> updateCart(@CookieValue(name = "guestId") String guestId,
                                                   CartRequest request) {
        OrderItems orderItems = memoryService.update(guestId, new OrderItems(request));
        return ResponseEntity.ok(new CartResponse(orderItems));
    }

    @DeleteMapping("/{seq}") // 특정 제품 제거
    public ResponseEntity<Void> removeFromCart(@CookieValue(name = "guestId") String guestId,
                                                @PathVariable Long seq) {
        memoryService.remove(guestId, seq);
        return ResponseEntity.ok().build();
    }
}
