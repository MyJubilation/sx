package com.sx.userserver.billiardsTable.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateBilliards {
    private Integer id;
    private float price;
    private Integer userId;
    private LocalDateTime residualTime;
}
