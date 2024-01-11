package com.purehub.config;



import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 龚圆康
 * @class 软件2144
 * @student_number 21040147
 * @Time 2024/1/10
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                // 接口文档标题
                .info(new Info().title("小额贷款系统接口文档")
                        // 接口文档简介
                        .description("这是基于Knife4j OpenApi3的接口文档")
                        // 接口文档版本
                        .version("v1.0")
                        // 开发者联系方式
                        .contact(new Contact().name("徐建豪, 龚安, 龚圆康").email("2270499893@qq.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot基础框架")
                        .url("http://localhost:8070"));
    }


}