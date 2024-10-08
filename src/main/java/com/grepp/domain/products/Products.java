package com.grepp.domain.products;

import com.grepp.domain.cart.OrderItems;
import com.grepp.domain.products.controller.dto.ProductsRequest;
import com.grepp.global.BaseEntity;
import com.grepp.global.config.UUIDConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Products extends BaseEntity {

    @Id
//    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(strategy = GenerationType.UUID)
//    @Convert(converter = UUIDConverter.class)
    private UUID productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Long price;

    private String description;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems = new ArrayList<>();

    public void updateByRequest(ProductsRequest request) {
        this.productName = request.getProductName();
        this.category = request.getCategory();
        this.price = request.getPrice();
        this.description = request.getDescription();
    }
}
