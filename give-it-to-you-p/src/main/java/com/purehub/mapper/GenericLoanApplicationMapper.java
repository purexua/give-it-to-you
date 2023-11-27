package com.purehub.mapper;

import com.purehub.pojo.GenericLoanApplication;
import com.purehub.pojo.PersonalProductInterestRate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Mapper
public interface GenericLoanApplicationMapper {
    @Select("select * from interest_rate")
    List<PersonalProductInterestRate> selectPersonalProductRate();

    @Insert("insert into generic_loan_application(user_id, product_type, term, requested_amount, interest_rate) " +
            "values (#{userId},#{productType},#{term},#{requestedAmount},#{interestRate})")
    int InsetGenericLoan(GenericLoanApplication loanApplication);
}
