package com.example.vo;

import lombok.Data;

import java.util.List;

@Data
public class OaListDto {
    List<OaListItem> oaDtoList;
    boolean ifNext;
}
