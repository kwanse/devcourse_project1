package com.grepp.domain.products;

import com.grepp.domain.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<Products, UUID> {


}
