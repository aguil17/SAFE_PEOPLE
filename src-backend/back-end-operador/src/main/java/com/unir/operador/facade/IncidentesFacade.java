package com.unir.operador.facade;
import com.unir.operador.model.request.CreateIncidenteBuscadorRequest;
import com.unir.operador.model.request.CreateIncidenteRequest;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
@Component
@Slf4j
public class IncidentesFacade {

    @Value("${incidentesBuscador.urlRegistro}")
    private String registrarIncidenteUrl;

    private final RestTemplate restTemplate;

    public void RegistrarIncidente(CreateIncidenteBuscadorRequest createIncidenteRequest)
    {
        String url = registrarIncidenteUrl;

        try
        {
            // Convertir el objeto a JSON para ver cómo se genera
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(createIncidenteRequest);

            // Imprimir el JSON para revisarlo
            log.info("JSON generado: {}", json);

            restTemplate.postForObject(url, createIncidenteRequest, String.class);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
    }
}

