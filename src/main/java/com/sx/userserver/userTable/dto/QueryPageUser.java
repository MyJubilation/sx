package com.sx.userserver.userTable.dto;

import lombok.Data;

@Data
public class QueryPageUser {
    private Long pageNumber;
    private Long pageSize;

    private String userName;
}
