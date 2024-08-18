package com.sx.userserver.billiardsTable.dto;

import lombok.Data;

@Data
public class LoginUser {
    private String username;
    private String password;
    // 添加图片验证码
    private String vcode;

}
