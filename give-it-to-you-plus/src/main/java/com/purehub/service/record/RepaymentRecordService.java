package com.purehub.service.record;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purehub.common.result.R;
import com.purehub.pojo.RepaymentRecord;
import com.purehub.pojo.RepaymentResult;

import java.util.List;

public interface RepaymentRecordService extends IService<RepaymentRecord> {
    public Boolean updatePaymentDateAndActualAmount(RepaymentRecord record);
    public Boolean insertFirstRecord(RepaymentRecord record);
    public Boolean isEnough(Double need, Integer userId);
    public Boolean changeCurrentTerm(Integer planId);
    public Boolean changeUserWallet(RepaymentRecord record);
    public Boolean addRecordsBatch(List<RepaymentRecord> records);
    public Boolean addOneRecords(RepaymentRecord record);
}
