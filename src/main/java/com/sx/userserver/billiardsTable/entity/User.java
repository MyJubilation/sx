package com.sx.userserver.billiardsTable.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author sdx2009
 * @since 2024-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 剩余余额
     */
    @TableField("money")
    private float money;

    /**
     * 头像的URL路径
     */
    @TableField("img_url")
    private String imgUrl;

    /**
     * 创建时间
     * 格式：YYY-MM-DDTHH：MM：SS，中间用“ T ”隔开
     * 前端可能传送格式为：TTT-MM-DD HH：MM：SS，中间为空格
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @TableField("operate")
    private String operate;


}
