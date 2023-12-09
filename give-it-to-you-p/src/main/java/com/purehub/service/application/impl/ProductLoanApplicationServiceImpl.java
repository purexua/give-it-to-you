package com.purehub.service.application.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.ProductLoanApplicationMapper;
import com.purehub.pojo.LoanApplication;
import com.purehub.service.application.ProductLoanApplicationService;
import org.springframework.stereotype.Service;

@Service
public class ProductLoanApplicationServiceImpl extends ServiceImpl<ProductLoanApplicationMapper, LoanApplication> implements ProductLoanApplicationService {
}
