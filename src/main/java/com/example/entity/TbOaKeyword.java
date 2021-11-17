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
public class TbOaKeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ???? ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * tb_oa.id
     */
    private Long oaId;

    /**
     * tb_keyword.id
     */
    private Long keywordId;

    @TableField("createTime")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
