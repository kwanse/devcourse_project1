package com.grepp.domain.orders.controller.dto;

import com.grepp.domain.orders.Orders;
import jakarta.persistence.Column;

import java.util.UUID;

public class OrdersResponse {

    private UUID orderId;
    private String email;
    private String address;
    private String postcode;
    private String orderStatus;

    public OrdersResponse(Orders orders) {
        this.orderId = orders.getOrderId();
        this.email = orders.getEmail();
        this.address = orders.getAddress();
        this.postcode = orders.getPostcode();
        this.orderStatus = orders.getOrderStatus();
    }
}
