package com.purehub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purehub.pojo.GenericLoanApplication;
import com.purehub.pojo.PersonalProductInterestRate;
import com.purehub.pojo.ProductInterestRate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductInterestRateMapper extends BaseMapper<ProductInterestRate> {

    @Select("select * from interest_rate")
    List<PersonalProductInterestRate> selectPersonalProductRate();

    @Insert("insert into generic_loan_application(user_id, product_type, term, requested_amount, interest_rate) " +
            "values (#{userId},#{productType},#{term},#{requestedAmount},#{interestRate})")
    int InsetGenericLoan(GenericLoanApplication loanApplication);
}
