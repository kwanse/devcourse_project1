package com.grepp.domain.orders.service;

import com.grepp.domain.orders.Orders;
import com.grepp.domain.orders.OrdersRepository;
import com.grepp.global.exception.NoDataException;
import com.grepp.global.exception.TimeExceedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.grepp.global.Const.*;

@RequiredArgsConstructor
@Transactional
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
    public Orders cancelByEmail(String email, UUID id, LocalDateTime currentTime) {

        LocalDateTime limitTime = currentTime.withHour(DELIVERY_LIMIT_HOUR);
        if (currentTime.isAfter(limitTime)) {
            throw new TimeExceedException(CANCELLATION_TIME_EXCEED);
        }
        Orders orders = ordersRepository.findByEmail(email).stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst().orElseThrow(() -> new NoDataException(NOT_FOUND_PRODUCTS));
        return orders.updateStatus(PAYMENT_CANCEL);
    }

    @Override
    public List<Orders> validateGuest(String email, String password) {
        return ordersRepository.findByEmailAndPassword(email, password);
    }
}
