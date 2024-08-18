package com.sx.userserver.foodTable.dto;

import lombok.Data;

@Data
public class AddFood {
    private Integer id;
    private String foodName;
    private Integer foodAccount;
    private String imgUrl;
    private float foodPrice;
}
