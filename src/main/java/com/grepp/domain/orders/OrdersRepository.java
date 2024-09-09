package com.grepp.domain.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {

    List<Orders> findByEmail(String email);
    List<Orders> findByEmailAndPassword(String email, String password);
}
