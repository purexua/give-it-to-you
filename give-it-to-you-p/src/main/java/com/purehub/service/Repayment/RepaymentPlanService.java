package com.purehub.service.Repayment;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purehub.pojo.RepaymentPlan;
import org.springframework.stereotype.Service;

public interface RepaymentPlanService extends IService<RepaymentPlan> {
    public Integer findTotalTerm(Long applicationId);
    public Integer findNowInstall(Long applicationId);
}
