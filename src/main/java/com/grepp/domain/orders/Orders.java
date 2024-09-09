package com.grepp.domain.orders;

import com.grepp.domain.cart.OrderItems;
import com.grepp.domain.orders.controller.dto.OrdersRequest;
import com.grepp.global.BaseEntity;
import com.grepp.global.config.UUIDConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.grepp.global.Const.PAYMENT_COMPLETED;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(columnDefinition = "BINARY(16)")
//    @Convert(converter = UUIDConverter.class)
    private UUID orderId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private String orderStatus;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems = new ArrayList<>();

    public Orders updateStatus(String status) {
        this.orderStatus = status;
        return this;
    }

    public Orders(List<OrderItems> orderItems, OrdersRequest request) {
        this.orderItems = orderItems;
        this.email = request.email();
        this.password = request.password();
        this.address = request.address();
        this.postcode = request.postcode();
        this.orderStatus = PAYMENT_COMPLETED;
    }

}
