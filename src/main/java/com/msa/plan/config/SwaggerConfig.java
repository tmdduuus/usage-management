package com.msa.plan.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("요금제 관리 시스템 API")
                        .version("1.0")
                        .description("요금제 조회 및 변경, 알림 관리를 위한 API"));
    }
}
