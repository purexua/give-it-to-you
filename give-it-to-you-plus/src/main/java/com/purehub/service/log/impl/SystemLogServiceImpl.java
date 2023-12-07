package com.purehub.service.log.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purehub.mapper.SystemLogMapper;
import com.purehub.pojo.SystemLog;
import com.purehub.service.log.SystemLogService;
import org.springframework.stereotype.Service;

@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements SystemLogService {
}
