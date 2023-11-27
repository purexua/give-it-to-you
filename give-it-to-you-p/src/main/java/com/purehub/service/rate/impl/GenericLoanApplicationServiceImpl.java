package com.purehub.service.rate.impl;

import com.purehub.mapper.GenericLoanApplicationMapper;
import com.purehub.mapper.ProductInterestRateMapper;
import com.purehub.pojo.GenericLoanApplication;
import com.purehub.pojo.PersonalProductInterestRate;
import com.purehub.service.rate.GenericLoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * #author 龚圆康
 * #class 软件2144
 * #student_number 21040147
 */
@Service
public class GenericLoanApplicationServiceImpl implements GenericLoanApplicationService {
    @Autowired
    private GenericLoanApplicationMapper genericLoanApplicationMapper;

    @Override
    public List<PersonalProductInterestRate> getPersonalProductRate() {
        return genericLoanApplicationMapper.selectPersonalProductRate();
    }

    @Override
    public Boolean InsertGenericLoan(GenericLoanApplication loanApplication) {
        int row = genericLoanApplicationMapper.InsetGenericLoan(loanApplication);
        return row==1? true:false;
    }
}
