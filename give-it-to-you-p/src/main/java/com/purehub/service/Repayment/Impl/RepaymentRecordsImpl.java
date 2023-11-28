package com.purehub.service.Repayment.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.RepaymentRecordsMapper;
import com.purehub.pojo.RepaymentRecord;
import com.purehub.service.Repayment.RepaymentRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RepaymentRecordsImpl implements RepaymentRecordsService {
    @Autowired
    private RepaymentRecordsMapper repaymentRecordsMapper;
    public List<RepaymentRecord> findAllRecords(Integer userId){
        return repaymentRecordsMapper.findAllRecords(userId);
    }

    @Override
    public void updateRecords(LocalDate date, Integer applicationId) {
        Integer planId = repaymentRecordsMapper.findPlanId(applicationId);
        repaymentRecordsMapper.updateTimeAndAmount(date, planId);
    }

    @Override
    public void insertRecord(LocalDate date, Integer appliationId, Integer amount) {
        Integer planId = repaymentRecordsMapper.findPlanId(appliationId);
        repaymentRecordsMapper.repaymentInsertRecords(planId, date, amount);
    }
}

