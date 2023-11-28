package com.purehub.service.Repayment;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purehub.pojo.RepaymentRecord;

import java.time.LocalDate;
import java.util.List;

public interface RepaymentRecordsService{
    public List<RepaymentRecord> findAllRecords(Integer userId);
    public void updateRecords(LocalDate date, Integer applicationId);
    public void insertRecord(LocalDate date, Integer applicationId, Integer amount);
}
