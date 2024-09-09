package com.grepp.domain.orders.service;

import com.grepp.domain.orders.Orders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrdersUseCase {

    List<Orders> getAllOrders();
    List<Orders> getAllOrdersByEmail(String email);
    Orders getOrdersById(UUID id);
    Orders addOrders(Orders orders);
    Orders cancelByEmail(String email, UUID ordersId, LocalDateTime currentTime);
    List<Orders> validateGuest(String email, String password);
//    Orders updateOrdersStatus();

}
