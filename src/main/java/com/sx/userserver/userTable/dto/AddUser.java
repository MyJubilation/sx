package com.sx.userserver.userTable.dto;

import lombok.Data;

@Data
public class AddUser {
    private String userName;
    private Integer userPassword;
    private Float remainMoney;
    private String userStatus;
}
