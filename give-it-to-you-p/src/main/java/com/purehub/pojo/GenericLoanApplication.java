package com.purehub.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */

@Data
public class GenericLoanApplication {
    private Integer applicationId;
    private Integer userId;
    private String productType;
    private Integer term;
    private Long requestedAmount;
    private Double interestRate;
}
