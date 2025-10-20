package com.springproject.store.cart;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(){
        super("Cart not found");
    }
}
