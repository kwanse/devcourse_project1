package com.grepp.domain.orders.controller.dto;

import com.grepp.domain.cart.OrderItems;
import com.grepp.domain.orders.Orders;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class OrdersResponse {

    private final UUID orderId;
    private final String email;
    private final String address;
    private final String postcode;
    private final String orderStatus;
    private final List<OrderItems> orderItems;

    public OrdersResponse(Orders orders) {
        this.orderId = orders.getOrderId();
        this.email = orders.getEmail();
        this.address = orders.getAddress();
        this.postcode = orders.getPostcode();
        this.orderStatus = orders.getOrderStatus();
        this.orderItems = orders.getOrderItems();
    }
}
