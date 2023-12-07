package com.purehub.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RepaymentAboutMapper {
    public Integer findTotalTerm(Long applicationId);
    public Integer findNowInstallment(Long applicationId);
}
