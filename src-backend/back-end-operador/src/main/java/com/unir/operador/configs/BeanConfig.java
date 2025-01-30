package com.unir.operador.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Generated;
import org.springframework.context.annotation.Bean;

@Generated("BeanConfig")
public class BeanConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
