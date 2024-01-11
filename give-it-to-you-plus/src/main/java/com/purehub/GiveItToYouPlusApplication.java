package com.purehub;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc  //json数据处理,必须使用此注解,因为他会加入json处理器
@MapperScan("com.purehub.mapper")
@SpringBootApplication
public class GiveItToYouPlusApplication {

  public static void main(String[] args) {
    SpringApplication.run(GiveItToYouPlusApplication.class, args);
  }

  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    //mybatis-plus 的插件集合
    MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
    mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
    mybatisPlusInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
    return mybatisPlusInterceptor;
  }
}
