package com.example.vo;

import com.example.entity.TbUser;
import lombok.Data;

@Data
public class UserDto extends TbUser {
    String password;
    String token;
}
