package com.springproject.store.order;

import com.springproject.store.dtos.OrderProductDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private OrderProductDto product;
    private int quantity;
    private BigDecimal totalPrice;
}
