package com.unir.operador.facade;
import com.unir.operador.model.request.CreateIncidenteRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Slf4j
public class IncidentesFacade {

    @Value("${incidentesBuscador.urlRegistro}")
    private String registrarIncidenteUrl;

    private final RestTemplate restTemplate;

    public void RegistrarIncidente(CreateIncidenteRequest createIncidenteRequest)
    {
        String url = registrarIncidenteUrl;

        try
        {
            restTemplate.postForObject(url, createIncidenteRequest, String.class);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
    }
}
