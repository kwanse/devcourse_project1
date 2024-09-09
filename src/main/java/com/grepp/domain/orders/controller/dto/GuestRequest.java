package com.grepp.domain.orders.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GuestRequest {
    private String email;
    private String password;
}
