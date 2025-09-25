package com.springproject.store.mappers;

import com.springproject.store.dtos.RegisterUserRequest;
import com.springproject.store.dtos.UpdateUserRequest;
import com.springproject.store.dtos.UserDto;
import com.springproject.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //Customizing the Mapping code
    @Mapping(target = "createdAt" , expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request,@MappingTarget User user);
}
