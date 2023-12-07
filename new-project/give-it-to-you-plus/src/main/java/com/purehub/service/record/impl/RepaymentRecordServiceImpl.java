package com.purehub.service.record.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.RepaymentRecordMapper;
import com.purehub.pojo.RepaymentRecord;
import com.purehub.service.record.RepaymentRecordService;
import org.springframework.stereotype.Service;

@Service
public class RepaymentRecordServiceImpl extends ServiceImpl<RepaymentRecordMapper, RepaymentRecord> implements RepaymentRecordService {
}
