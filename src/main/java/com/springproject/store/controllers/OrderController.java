package com.springproject.store.controllers;


import com.springproject.store.common.ErrorDto;
import com.springproject.store.dtos.OrderDto;
import com.springproject.store.exceptions.OrderNotFoundException;
import com.springproject.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;


    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();

    }
    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId){
        return orderService.getOrder("orderId");
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Void> handleOrderNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleAccessDenied(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorDto("Access denied"));
    }
}
