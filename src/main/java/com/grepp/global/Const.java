package com.grepp.global;

import java.time.LocalTime;

public final class Const {

    public static final String NOT_FOUND_PRODUCTS = "물건이 존재하지 않습니다!";
    public static final String NOT_FOUND_IN_THE_CART = "장바구니에 해당 물건이 없습니다!";
    public static final String NO_ITEM_IN_THE_CART = "장바구니가 비었습니다!";
    public static final String CANCELLATION_TIME_EXCEED = "취소가능 시간이 지났습니다!";

    public static final String INVALID_ADMIN = "존재하지 않는 admin 아이디입니다";
    public static final String INVALID_GUEST = "아이디 혹은 비밀번호가 잘못되었습니다";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";

    public static final String ISSUER = "kwan";

    public static final String PAYMENT_COMPLETED = "결제 완료";
    public static final String PAYMENT_CANCEL = "결제 취소";
    public static final String DELIVERY_SUCCESS = "배달완료!";
    public static final String IN_DELIVERY = "배달중입니다";

    public static final int EXPIRE_TIME = 60 * 60 * 3;
    public static final int ACCESS_TOKEN_LIMIT_TIME = 1000 * 60;

    public static final int DELIVERY_LIMIT_HOUR = 13;

    private Const() {}
}
