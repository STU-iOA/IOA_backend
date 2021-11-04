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
 * @since 2021-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OAkeyword")
public class OAkeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "OAkeywordId", type = IdType.AUTO)
    private Long OAkeywordId;

    @TableField("OAId")
    private Long OAId;

    @TableField("keywordId")
    private Long keywordId;

    @TableField("createTime")
    private LocalDateTime createTime;


}
