package com.sx.userserver.billiardsTable.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserPermTree {
    private Integer id;
    private String permName;
    private String permType;
    private String permKey;
    private String permUrl;
    private List<UserPermTree> children;
}
