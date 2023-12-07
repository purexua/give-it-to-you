package com.purehub.service.credit.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.CreditScoreMapper;
import com.purehub.pojo.CreditScore;
import com.purehub.service.credit.CreditScoreService;
import org.springframework.stereotype.Service;

@Service
public class CreditScoreServiceImpl extends ServiceImpl<CreditScoreMapper, CreditScore> implements CreditScoreService {
}
