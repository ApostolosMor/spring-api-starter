package com.springproject.store.mappers;


import com.springproject.store.dtos.OrderDto;
import com.springproject.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
