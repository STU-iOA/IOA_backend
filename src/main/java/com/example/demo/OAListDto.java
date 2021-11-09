package com.example.demo;

import lombok.Data;

import java.util.List;

@Data
public class OAListDto {
    List<OADto> oaDtoList;
    boolean ifNext;
}
