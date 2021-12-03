package com.example.vo;

import com.example.entity.TbDaily;
import lombok.Data;

@Data
public class UserDailyDto extends TbDaily {
    public String token;
}
