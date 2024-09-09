package com.grepp.global.advice;

import lombok.Getter;

@Getter
public record ErrorResponse(String message) {
}
