package com.grepp.domain.orders.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class GuestResponse {
    private String guestName;
    private String token;
}
