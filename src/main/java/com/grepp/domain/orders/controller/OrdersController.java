package com.grepp.domain.orders.controller;

import com.grepp.domain.orders.service.OrdersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrdersController {

    private final OrdersUseCase ordersService;

    @GetMapping
    public ResponseEntity<List<>>
}
