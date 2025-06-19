package com.codewithmosh.store.dtos;

import lombok.Data;

@Data //Combination of @Getter and @Setter
public class RegisterUserDto {
    private String name;
    private String email;
    private String password;
}
