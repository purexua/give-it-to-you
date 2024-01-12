package com.purehub.service.plan.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.RepaymentPlanMapper;
import com.purehub.pojo.RepaymentPlan;
import com.purehub.service.plan.RepaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class RepaymentServiceImpl extends ServiceImpl <RepaymentPlanMapper, RepaymentPlan> implements RepaymentPlanService {
    @Autowired
    private RepaymentPlanMapper repaymentPlanMapper;
    public IPage<RepaymentPlan> selectPage(IPage<RepaymentPlan> page, Integer currentTerm, Integer applicationId)
    {
        return repaymentPlanMapper.selectPage(page, currentTerm, applicationId);
    }

}
