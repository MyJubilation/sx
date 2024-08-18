package com.sx.userserver.billiardsTable.dto;

import lombok.Data;

@Data
public class AddUser {
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private float money;
    private String imgUrl;


}
