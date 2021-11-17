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
 * @since 2021-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbKeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ???? ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String keyword;

    @TableField("createTime")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
