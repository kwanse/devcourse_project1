package com.grepp.domain.orders.service;

import com.grepp.domain.orders.OrderItems;
import com.grepp.global.MemoryService;
import com.grepp.global.exception.NoDataException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static com.grepp.global.Const.*;

@Service
public class MemoryOrderService implements MemoryService<OrderItems> {

    private final Map<String, List<OrderItems>> memory = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(10);

    @Override
    public List<OrderItems> get(String key) {

        if (!memory.containsKey(key) || key == null) {
            return new ArrayList<>();
        }
        return memory.get(key);
    }

    @Override
    public OrderItems put(String key, OrderItems value) {

        if (memory.containsKey(key)) {
            memory.get(key).add(value);
            return value;
        }
        memory.put(key, new ArrayList<>());
        memory.get(key).add(value);

        scheduler.schedule(() -> memory.remove(key), EXPIRE_TIME, TimeUnit.SECONDS);
        // 사용자가 요청을 보내면 그 시간을 기점으로 다시 추가하는것은 안될까?

        return value;
    }

    @Override
    public OrderItems update(String key, OrderItems value) {
        
        List<OrderItems> cart = memory.get(key);
        Long valueSeq = value.getSeq();
        int index = getIndexFromCart(valueSeq, cart);

        cart.set(index, value);
        return cart.get(index);
    }

    @Override
    public void remove(String key, Long valueSeq) {

        List<OrderItems> cart = memory.get(key);
        int index = getIndexFromCart(valueSeq, cart);

        cart.remove(index);
    }

    private static int getIndexFromCart(Long valueSeq, List<OrderItems> cart) {

        return IntStream.range(0, cart.size())
                .filter(i -> cart.get(i).getSeq().equals(valueSeq) )
                .findFirst()
                .orElseThrow(() -> new NoDataException(NOT_FOUND_IN_THE_CART));
    }

    @Override
    public void removeAll(String key) {
        memory.remove(key);
    }
}
