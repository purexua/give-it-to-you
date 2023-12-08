package com.purehub.service.Repayment.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.RepaymentAboutMapper;
import com.purehub.mapper.RepaymentPlanMapper;
import com.purehub.pojo.RepaymentPlan;
import com.purehub.service.Repayment.RepaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepaymentServiceImpl extends ServiceImpl <RepaymentPlanMapper, RepaymentPlan> implements RepaymentPlanService {
    @Autowired
    private RepaymentAboutMapper repaymentAboutMapper;
    @Override
    public Integer findTotalTerm(Long applicationId) {
        return repaymentAboutMapper.findTotalTerm(applicationId);
    }

    @Override
    public Integer findNowInstall(Long applicationId) {
        return repaymentAboutMapper.findNowInstallment(applicationId);
    }
}
