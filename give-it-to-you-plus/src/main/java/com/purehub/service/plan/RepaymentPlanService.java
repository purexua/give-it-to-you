package com.purehub.service.plan;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.purehub.pojo.RepaymentPlan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface RepaymentPlanService extends IService<RepaymentPlan> {
    public IPage<RepaymentPlan> selectPage(IPage<RepaymentPlan> page, Integer currentTerm, Integer applicationId);
}
