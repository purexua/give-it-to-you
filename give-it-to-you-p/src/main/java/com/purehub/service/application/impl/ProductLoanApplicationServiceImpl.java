package com.purehub.service.application.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.ProductLoanApplicationMapper;
import com.purehub.pojo.ProductLoanApplication;
import com.purehub.service.application.ProductLoanApplicationService;
import org.springframework.stereotype.Service;

@Service
public class ProductLoanApplicationServiceImpl extends ServiceImpl<ProductLoanApplicationMapper, ProductLoanApplication> implements ProductLoanApplicationService {
}
