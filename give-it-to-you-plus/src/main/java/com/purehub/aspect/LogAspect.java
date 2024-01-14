package com.purehub.aspect;

import com.alibaba.fastjson.JSON;
import com.purehub.annotation.OperationLog;
import com.purehub.pojo.SystemLog;
import com.purehub.service.log.SystemLogService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author 龚圆康
 * @class 软件2144
 * @student_number 21040147
 * @Time 2024/1/14
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private SystemLogService systemLogService;
    /**
    * @Pointcut 定义切点
    * */
    @Pointcut("@annotation(com.purehub.annotation.OperationLog)")
    public void logPointCut(){}

    @AfterReturning(value = "logPointCut() && @annotation(logger)",returning = "ret")
    public void saveOperationLog(JoinPoint joinPoint,OperationLog logger,Object ret) throws IOException {
        //创建一个操作日志实体类存放此次操作信息
        SystemLog systemLog = new SystemLog();
        //操作人
        systemLog.setUserId(logger.userId());
        //操作时间
//        systemLog.setLogTime(logger.logTime());
        //操作方法
        systemLog.setAction(logger.action());
        systemLogService.save(systemLog);
    }
}
