package com.grepp.domain.orders.service;

import com.grepp.domain.orders.Orders;

import java.util.List;
import java.util.UUID;

public interface OrdersUseCase {

    List<Orders> getAllOrders();
    List<Orders> getAllOrdersByEmail(String email);
    Orders getOrdersById(UUID id);
    Orders addOrders(Orders orders);
    void removeByEmail(String email, UUID ordersId);

}
