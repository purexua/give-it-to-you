package com.purehub.service.plan.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.RepaymentPlanMapper;
import com.purehub.pojo.RepaymentPlan;
import com.purehub.service.plan.RepaymentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepaymentServiceImpl extends ServiceImpl <RepaymentPlanMapper, RepaymentPlan> implements RepaymentPlanService {

}
