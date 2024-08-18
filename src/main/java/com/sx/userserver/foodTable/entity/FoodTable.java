package com.sx.userserver.foodTable.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("food_table")
public class FoodTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 食品名称
     */
    @TableField("food_name")
    private String foodName;

    /**
     * 食品价格
     */
    @TableField("food_price")
    private Float foodPrice;

    /**
     * 剩余数量
     */
    @TableField("food_account")
    private Integer foodAccount;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("img_url")
    private String imgUrl;


}