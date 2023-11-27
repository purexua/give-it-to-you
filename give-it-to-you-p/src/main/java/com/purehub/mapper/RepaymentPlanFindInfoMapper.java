package com.purehub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purehub.pojo.GenericLoanApplication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RepaymentPlanFindInfoMapper extends BaseMapper<GenericLoanApplication> {
}
