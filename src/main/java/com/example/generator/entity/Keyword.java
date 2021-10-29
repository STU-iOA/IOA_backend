package com.example.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author www
 * @since 2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Keyword implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "keywordId", type = IdType.AUTO)
    private Long keywordId;

    private String name;

    @TableField("createTime")
    private LocalDateTime createTime;


}
