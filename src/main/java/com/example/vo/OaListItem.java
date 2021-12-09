package com.example.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OaListItem {
    private Long id;
    private String title;
    private LocalDateTime timestamp;
    private String subcompanyName;
    private String keywords;
    private Integer favoredCount;
    private Integer readCount;
    private String keyText;
}
