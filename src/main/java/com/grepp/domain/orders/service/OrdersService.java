package com.grepp.domain.orders.service;

import com.grepp.domain.orders.Orders;
import com.grepp.domain.orders.OrdersRepository;
import com.grepp.global.exception.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.grepp.global.Const.NOT_FOUND_PRODUCTS;

@RequiredArgsConstructor
@Service
public class OrdersService implements OrdersUseCase{

    private final OrdersRepository ordersRepository;

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public List<Orders> getAllOrdersByEmail(String email) {
        return ordersRepository.findByEmail(email);
    }

    @Override
    public Orders getOrdersById(UUID id) {
        return ordersRepository.findById(id).orElseThrow(()
                -> new NoDataException(NOT_FOUND_PRODUCTS));
    }

    @Override
    public Orders addOrders(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public void removeByEmail(String email, UUID id) {
        Orders orders = ordersRepository.findByEmail(email).stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst().orElseThrow(() -> new NoDataException(NOT_FOUND_PRODUCTS));

        ordersRepository.delete(orders);
    }
}
