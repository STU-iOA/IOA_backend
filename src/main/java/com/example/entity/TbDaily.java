package com.example.entity;

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
 * @since 2021-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dailyId", type = IdType.AUTO)
    private Long dailyId;

    private LocalDateTime date;

    @TableField("timesTamp")
    private Long timesTamp;

    private String conclusion;

    private String content;

    @TableField("ifComplete")
    private Integer ifComplete;

    @TableField("userId")
    private Long userId;


}
