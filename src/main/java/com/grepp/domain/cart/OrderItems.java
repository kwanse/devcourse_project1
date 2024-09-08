package com.grepp.domain.cart;

import com.grepp.domain.cart.controller.CartRequest;
import com.grepp.domain.orders.Orders;
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
        this.seq = request.seq();
        this.products = request.products();
        this.category = request.category();
        this.price = request.price();
        this.quantity = request.quantity();
    }
}
