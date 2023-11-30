package com.purehub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purehub.pojo.CreditScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CreditScoreMapper extends BaseMapper<CreditScore> {

    @Update("update credit_score set limit_amount=limit_amount-#{requestedAmount} where user_id=#{userId}")
    int updateByUserId(int userId, Double requestedAmount);
}
