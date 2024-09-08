package com.grepp.domain.orders.controller;

import com.grepp.domain.orders.controller.dto.OrdersRequest;
import com.grepp.domain.orders.controller.dto.OrdersResponse;
import com.grepp.domain.orders.service.OrdersUseCase;
import com.grepp.global.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrdersController {

    private final OrdersUseCase ordersService;
    private final CacheService cacheOrderService;


    @GetMapping("/{ordersId}") // 주문 상세 정보
    public ResponseEntity<OrdersResponse> getOrderDetail(@AuthenticationPrincipal String email
            , @PathVariable Long ordersId) {


        // findByEmail()
    }


    @PostMapping("/add")
    public ResponseEntity<OrdersResponse> addOrders(OrdersRequest request) {

        // request 로 OrderItems 생성
    }

    @PutMapping("/{ordersId}")
    public ResponseEntity<OrdersResponse> updateOrders() {

    }

    @PostMapping("/{ordersId}")
}
