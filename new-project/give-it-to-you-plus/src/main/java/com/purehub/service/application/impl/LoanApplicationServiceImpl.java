package com.purehub.service.application.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.LoanApplicationMapper;
import com.purehub.pojo.LoanApplication;
import com.purehub.service.application.LoanApplicationService;
import org.springframework.stereotype.Service;

@Service
public class LoanApplicationServiceImpl extends ServiceImpl<LoanApplicationMapper, LoanApplication> implements LoanApplicationService {
}
