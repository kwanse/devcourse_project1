package com.grepp.global;

import com.grepp.domain.orders.OrderItems;

import java.util.List;

public interface MemoryService<T> {

    List<OrderItems> get(String key);
    T put(String key, T value);
    T update(String key, T value);
    void remove(String key, Long valueSeq);
    void removeAll(String key);

}
