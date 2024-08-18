package com.sx.userserver.userTable.dto;

import lombok.Data;

@Data
public class UpdateUser {
    private Integer userId;
    private String userName;
    private Integer userPassword;
    private Float remainMoney;
    private String userStatus;
}
