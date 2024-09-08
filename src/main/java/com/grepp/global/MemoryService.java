package com.grepp.global;

import com.grepp.domain.cart.OrderItems;

import java.util.List;

public interface MemoryService<T> {

    List<OrderItems> get(String key);
    T add(String key, T value);
    T update(String key, T value);
    void remove(String key, Long valueSeq);
    void removeAll(String key);

}
