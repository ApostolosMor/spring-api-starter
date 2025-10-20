package com.springproject.store.order;


import com.springproject.store.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private AuthService authService;
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;


    public List<OrderDto> getAllOrders(){

        var user = authService.getCurrentUser();
        var orders = orderRepository.getOrdersByCustomer(user);

        return orders.stream().map(orderMapper::toDto).toList();

    }

    public OrderDto getOrder(String orderId) {

        var order = orderRepository.getOrderWithItems(orderId)
                .orElseThrow(OrderNotFoundException::new);


        var user = authService.getCurrentUser();
        if (order.isPlacedBy(user)) {
            throw new AccessDeniedException("You do not have access to this order");
        }
        return orderMapper.toDto(order);
    }
}
