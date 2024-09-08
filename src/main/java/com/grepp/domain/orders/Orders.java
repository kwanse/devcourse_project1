package com.grepp.domain.orders;

import com.grepp.global.BaseEntity;
import com.grepp.global.UUIDConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Orders extends BaseEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDConverter.class)
    private UUID orderId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private String orderStatus;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems;

}
