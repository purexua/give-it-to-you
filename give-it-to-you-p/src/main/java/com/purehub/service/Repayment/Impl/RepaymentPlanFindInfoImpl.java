package com.purehub.service.Repayment.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.RepaymentPlanFindInfoMapper;
import com.purehub.pojo.LoanApplication;
import com.purehub.service.Repayment.RepaymentPlanFindInfoService;
import org.springframework.stereotype.Service;

@Service
public class RepaymentPlanFindInfoImpl extends ServiceImpl<RepaymentPlanFindInfoMapper, LoanApplication> implements RepaymentPlanFindInfoService {
}
