package com.purehub.service.rate.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.ProductInterestRateMapper;
import com.purehub.pojo.ProductInterestRate;
import com.purehub.service.rate.ProductInterestRateService;
import org.springframework.stereotype.Service;

@Service
public class ProductInterestRateServiceImpl extends ServiceImpl<ProductInterestRateMapper, ProductInterestRate> implements ProductInterestRateService {
}
