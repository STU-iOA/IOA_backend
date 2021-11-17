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
public class TbUserFavorites implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ???? ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * tb_user.id
     */
    private Long userId;

    /**
     * tb_oa.id
     */
    private Long oaId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
