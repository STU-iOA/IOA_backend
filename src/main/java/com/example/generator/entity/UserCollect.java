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
@TableName("userCollect")
public class UserCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * S
     */
    @TableId(value = "userCollectId", type = IdType.AUTO)
    private Long userCollectId;

    @TableField("userId")
    private Long userId;

    @TableField("OAId")
    private Long OAId;

    private LocalDateTime time;


}
