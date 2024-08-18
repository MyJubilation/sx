package com.sx.userserver.adminTable.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Beetles
 * @since 2024-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("admin_table")
public class AdminTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员用户名
     */
    @TableField("adm_name")
    private String admName;

    /**
     * 密码
     */
    @TableField("adm_password")
    private Integer admPassword;


}
