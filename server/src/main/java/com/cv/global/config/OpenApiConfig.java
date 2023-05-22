package com.cv.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("이력서 자동완성 애플리케이션")
                .version("v1.0.0")
                .description(" 이 문서는 Frontend(김희진, 강동욱, 김도현)분들과 Backend(박은희, 권채연, 유성영, 김재윤)분들이 함께 진행하는 이력서 자동완성 애플리케이션 프로젝트입니다.\n" +
                        "이 애플리케이션을 사용해보고자 하는 분들은 이 문서를 통해 API의 구체적인 사용법을 알 수 있습니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
