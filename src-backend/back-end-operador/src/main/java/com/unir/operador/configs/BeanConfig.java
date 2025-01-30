package com.unir.operador.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unir.operador.util.ExcludeFromCoverage;
import org.springframework.context.annotation.Bean;

@ExcludeFromCoverage
public class BeanConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
