package com.purehub.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.Data;

import java.lang.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

/**
 * @author 龚圆康
 * @class 软件2144
 * @student_number 21040147
 * @Time 2024/1/14
 */


@Target(value = ElementType.METHOD)//用于描述方法
@Retention(RetentionPolicy.RUNTIME)//注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
@Documented
public @interface OperationLog {
    //操作人
    int userId() default 1;
    //操作时间
    String logTime() default "";
    //操作类型
    String action() default "";
}
