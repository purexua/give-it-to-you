package com.purehub.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RepaymentRecord {
    private Long applicationId;
    private String productType;
    private Integer installment;//当前期数
    private Double actualAmount;//已还金额
    private LocalDate paymentDate;

}
