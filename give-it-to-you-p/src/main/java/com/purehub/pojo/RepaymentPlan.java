package com.purehub.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RepaymentPlan {
    private Integer applicationId;
    private Integer installment;
    private LocalDate dueDate;
    private Integer amountDue;
    private String paymentStatus;
    private Integer userId;

    @TableLogic
    private Integer deleted;
}
