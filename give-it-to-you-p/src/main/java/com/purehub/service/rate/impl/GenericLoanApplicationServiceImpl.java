package com.purehub.service.rate.impl;

import com.purehub.mapper.*;
import com.purehub.pojo.GenericLoanApplication;
import com.purehub.pojo.PersonalProductInterestRate;
import com.purehub.pojo.RepaymentPlanPlus;
import com.purehub.service.rate.GenericLoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Service
public class GenericLoanApplicationServiceImpl implements GenericLoanApplicationService {
    @Autowired
    private GenericLoanApplicationMapper genericLoanApplicationMapper;
    @Autowired
    private CreditScoreMapper creditScoreMapper;
    @Autowired
    private RepaymentPlanPlusMapper repaymentPlanPlusMapper;

    @Override
    public List<PersonalProductInterestRate> getPersonalProductRate() {
        return genericLoanApplicationMapper.selectPersonalProductRate();
    }

    @Override
    @Transactional
    public Boolean InsertGenericLoan(GenericLoanApplication loanApplication) {
        int applicationId = genericLoanApplicationMapper.InsetGenericLoan(loanApplication);
        int userId = loanApplication.getUserId(); //获取用户id
        Long requestedAmount = loanApplication.getRequestedAmount(); //获取申请的贷款

        creditScoreMapper.updateByUserId(userId,requestedAmount);  //扣除信誉额度

        Integer term = loanApplication.getTerm(); //获取还款期数
        Double interestRate = loanApplication.getInterestRate(); //获取利率
//        Double monthlyInterestRate = interestRate / 12; //每月利率
        Double totalAmount = requestedAmount * (1 + interestRate);  //总共需还金额
        Double installmentAmount = totalAmount / term;  //每月需还金额
        LocalDate nextMonth = LocalDate.now().plusMonths(1); //当前时间+1个月

        RepaymentPlanPlus repaymentPlanPlus = new RepaymentPlanPlus();
        repaymentPlanPlus.setApplicationId(applicationId);
        repaymentPlanPlus.setInstallment(1); //设置当前还款的周期数，默认第一次还款为1
        repaymentPlanPlus.setAmountDue(installmentAmount);
        repaymentPlanPlus.setDueDate(nextMonth);
        repaymentPlanPlus.setUserId(userId);
        repaymentPlanPlus.setPaymentStatus("unpaid"); //设置还款状态默认，未还款(unpaid)

        repaymentPlanPlusMapper.insert(repaymentPlanPlus);//插入还款计划
        return true;
    }
}
