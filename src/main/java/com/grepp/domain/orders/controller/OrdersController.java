package com.grepp.domain.orders.controller;

import com.grepp.domain.admin.Admin;
import com.grepp.domain.cart.OrderItems;
import com.grepp.domain.orders.Orders;
import com.grepp.domain.orders.controller.dto.GuestRequest;
import com.grepp.domain.orders.controller.dto.GuestResponse;
import com.grepp.domain.orders.controller.dto.OrdersRequest;
import com.grepp.domain.orders.controller.dto.OrdersResponse;
import com.grepp.domain.orders.service.OrdersUseCase;
import com.grepp.global.MemoryService;
import com.grepp.global.auth.JwtTokenProvider;
import com.grepp.global.exception.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.grepp.global.Const.ACCESS_TOKEN_LIMIT_TIME;
import static com.grepp.global.Const.INVALID_GUEST;


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
    public ResponseEntity<OrdersResponse> addOrders(@CookieValue(name = "guestId") String guestId,
                                                    @RequestBody OrdersRequest request) {

        List<OrderItems> orderItems = memoryOrderService.get(guestId);
        Orders orders = new Orders(orderItems, request);

        OrdersResponse response = new OrdersResponse(ordersService.addOrders(orders));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{ordersId}") // repository 에 지우진 않고 status 만 수정
    public ResponseEntity<OrdersResponse> cancelOrders(@AuthenticationPrincipal String email,
                                                       @PathVariable UUID ordersId,
                                                       LocalDateTime currentTime) {
        Orders orders = ordersService.cancelByEmail(email, ordersId, currentTime);
        return ResponseEntity.ok(new OrdersResponse(orders));
    }

    @PostMapping("/sing-in")
    public ResponseEntity<GuestResponse> singIn(@RequestBody GuestRequest request) {

        List<Orders> orders = ordersService.validateGuest(request.getEmail(), request.getPassword());
        if (orders == null || orders.isEmpty()) {
            throw new NoDataException(INVALID_GUEST);
        }
        String token = JwtTokenProvider.createAccessToken(new Admin(), ACCESS_TOKEN_LIMIT_TIME);
        GuestResponse response = new GuestResponse(request.getEmail(), token);
        return ResponseEntity.ok(response);
    }
}

