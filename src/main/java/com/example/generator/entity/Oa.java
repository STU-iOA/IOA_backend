package com.example.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("OA")
public class Oa implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "OAId", type = IdType.AUTO)
    private Long OAId;

    private String content;

    private LocalDateTime timestamp;

    private String title;

    @TableField("departmentId")
    private Long departmentId;

    private String keywords;

    @TableField("subscribeNumber")
    private Integer subscribeNumber;

    @TableField("collectNunber")
    private Integer collectNunber;


}
