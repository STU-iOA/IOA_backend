package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class TbOa implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * DOCSUBJECT
     */
    private String title;

    /**
     * DOCCONTENT
     */
    private String content;

    /**
     * DOCVALIDDATE + " " + DOCVALIDTIME
     */
    private LocalDateTime timestamp;

    /**
     * LOGINID
     */
    private String loginId;

    /**
     * LASTNAME
     */
    private String lastName;

    /**
     * SUBCOMPANYNAME
     */
    private String subcompanyName;

    /**
     * DEPARTMENTNAME
     */
    private String departmentName;

    private String keywords;

    /**
     * 收藏量
     */
    private Integer favoredCount;

    /**
     * 阅读量
     */
    private Integer readCount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 关键文本
     */
    private String keyText;
}
