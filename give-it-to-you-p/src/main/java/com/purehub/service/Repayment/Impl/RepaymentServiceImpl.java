package com.purehub.service.Repayment.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.RepaymentPlanMapper;
import com.purehub.pojo.RepaymentPlan;
import com.purehub.service.Repayment.RepaymentPlanService;
import org.springframework.stereotype.Service;

@Service
public class RepaymentServiceImpl extends ServiceImpl <RepaymentPlanMapper, RepaymentPlan> implements RepaymentPlanService {
}
