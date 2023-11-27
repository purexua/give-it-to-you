package com.purehub.service.rate.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.ProductInterestRateMapper;
import com.purehub.pojo.GenericLoanApplication;
import com.purehub.pojo.PersonalProductInterestRate;
import com.purehub.pojo.ProductInterestRate;
import com.purehub.service.rate.ProductInterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInterestRateServiceImpl extends ServiceImpl<ProductInterestRateMapper, ProductInterestRate> implements ProductInterestRateService {

    @Autowired
    private ProductInterestRateMapper productInterestRateMapper;

    @Override
    public List<PersonalProductInterestRate> getPersonalProductRate() {
        return productInterestRateMapper.selectPersonalProductRate();
    }

    @Override
    public Boolean InsertGenericLoan(GenericLoanApplication loanApplication) {
        int row = productInterestRateMapper.InsetGenericLoan(loanApplication);
        return row==1? true:false;
    }
}
