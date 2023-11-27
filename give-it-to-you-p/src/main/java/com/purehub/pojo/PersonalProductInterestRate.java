package com.purehub.pojo;

import lombok.Data;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */

@Data
public class PersonalProductInterestRate {
    private Integer rateId;
    private String productType;
    private Integer term;
    private Double interestRate;
}
