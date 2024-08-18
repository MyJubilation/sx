package com.sx.userserver.userTable.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("user_table")
public class UserTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户密码
     */
    @TableField("user_password")
    private Integer userPassword;

    /**
     * 剩余金额
     */
    @TableField("remain_money")
    private Float remainMoney;

    /**
     * 使用状态
     */
    @TableField("user_status")
    private String userStatus;


}
