package com.example.demo;

import com.example.generator.entity.User;
import lombok.Data;

@Data
public class UserDto extends User {
    String password;
    String token;
}
