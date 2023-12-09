package com.purehub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.purehub.pojo.RepaymentRecord;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper
public interface RepaymentRecordsMapper{
    public void repaymentInsertRecords(Integer planId, LocalDate paymentDate, Integer actualAmount);

    public List<RepaymentRecord> findAllRecords(Integer userId);
    public void updateTimeAndAmount(LocalDate date, Integer planId);
    public Integer findPlanId(Long applicationId);
}
