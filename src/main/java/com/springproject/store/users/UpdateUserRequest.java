package com.springproject.store.users;


import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
}
