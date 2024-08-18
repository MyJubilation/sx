package com.sx.userserver.foodTable.dto;

import lombok.Data;

@Data
public class UpdateFood {
    private Integer id;
    private String foodName;
    private Integer foodAccount;
    private float foodPrice;
    private String imgUrl;

}