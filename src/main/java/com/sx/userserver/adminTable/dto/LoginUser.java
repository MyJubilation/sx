package com.sx.userserver.adminTable.dto;

import lombok.Data;

@Data
public class LoginUser {
    private String admname;
    private String admpassword;
    public String vcode;
}
