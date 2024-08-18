package com.sx.userserver.foodTable.dto;

import lombok.Data;

@Data
public class QueryPageFood {
    private String foodName;
    private long pageNumber;
    private long pageSize;
}
