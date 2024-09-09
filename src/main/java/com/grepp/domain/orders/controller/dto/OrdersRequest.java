package com.grepp.domain.orders.controller.dto;

public record OrdersRequest(String email, String password, String address, String postcode) {

}
