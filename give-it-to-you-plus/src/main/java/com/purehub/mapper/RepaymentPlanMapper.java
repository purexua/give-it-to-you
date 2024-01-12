package com.purehub.mapper;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.purehub.pojo.RepaymentPlan;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper
public interface RepaymentPlanMapper extends BaseMapper<RepaymentPlan> {
    IPage<RepaymentPlan> selectPage(IPage<RepaymentPlan> page, Integer currentTerm, Integer applicationId);
}
