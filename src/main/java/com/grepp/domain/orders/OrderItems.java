package com.grepp.domain.orders;

import com.grepp.domain.orders.controller.dto.CartRequest;
import com.grepp.domain.products.Products;
import com.grepp.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItems extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Products products;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer quantity;

    public OrderItems(CartRequest request) {
        this.seq = request.getSeq();

        // this.orders = request.getOrders();
        this.products = request.getProducts();
        this.category = request.getCategory();
        this.price = request.getPrice();
        this.quantity = request.getQuantity();
    }
}
