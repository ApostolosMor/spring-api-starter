package com.springproject.store.cart;

public class CartEmptyException extends RuntimeException {

    public CartEmptyException() {
        super("Cart is empty");
    }
}
