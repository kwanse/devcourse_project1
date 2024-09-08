package com.grepp.domain.orders.controller;

import com.grepp.domain.cart.OrderItems;
import com.grepp.domain.orders.Orders;
import com.grepp.domain.orders.service.OrdersUseCase;
import com.grepp.global.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrdersController {

    private final OrdersUseCase ordersService;
    private final MemoryService<OrderItems> memoryOrderService;

    @GetMapping("/admin")
    public ResponseEntity<List<OrdersResponse>> getAllOrders() {

        List<Orders> orders = ordersService.getAllOrders();
        List<OrdersResponse> responses = orders.stream()
                .map(OrdersResponse::new).toList();

        return ResponseEntity.ok(responses);
    }


    @GetMapping
    public ResponseEntity<List<OrdersResponse>> getAllOrdersByEmail(@AuthenticationPrincipal String email) {

        List<Orders> orders = ordersService.getAllOrdersByEmail(email);
        List<OrdersResponse> responses = orders.stream()
                .map(OrdersResponse::new).toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{ordersId}")
    public ResponseEntity<OrdersResponse> getOrderDetail(@AuthenticationPrincipal String email
            , @PathVariable UUID ordersId) {

        Orders orders = ordersService.getOrdersById(ordersId);
        return ResponseEntity.ok(new OrdersResponse(orders));
    }

    @PostMapping
    public ResponseEntity<OrdersResponse> addOrders(@CookieValue(name = "guestId") String guestId) {

        List<OrderItems> orderItems = memoryOrderService.get(guestId);
        Orders orders = new Orders(orderItems);

        OrdersResponse response = new OrdersResponse(ordersService.addOrders(orders));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{ordersId}")
    public ResponseEntity<Void> cancelOrders(@AuthenticationPrincipal String email,
                                                       @PathVariable UUID ordersId) {

        ordersService.removeByEmail(email, ordersId);
        return ResponseEntity.ok().build();
    }
}
