package com.purehub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.purehub.mapper")
@SpringBootApplication
public class GiveItToYouBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(GiveItToYouBackendApplication.class, args);
  }

}
