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
@TableName("UserDepartment")
public class UserDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userDepartmentId", type = IdType.AUTO)
    private Long userDepartmentId;

    @TableField("userId")
    private Long userId;

    @TableField("departmentId")
    private Long departmentId;

    private LocalDateTime time;


}
