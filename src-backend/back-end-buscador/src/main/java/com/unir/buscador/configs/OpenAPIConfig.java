package com.unir.buscador.configs;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Buscador")
                        .version("1.0")
                        .description("Contiene las consultas para ser expuestas para los sistemas: policial, bomberos y hospitales"));
    }
}

