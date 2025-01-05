package com.unir.buscador.service;

import com.unir.buscador.data.IIncidenteRepository;
import com.unir.buscador.model.response.GetIncidenteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidenteServiceImpl implements IIncidenteService {

    @Autowired
    private IIncidenteRepository incidenteRepository;

    public GetIncidenteResponse getIncidentes() {

        var result = new GetIncidenteResponse();

        var resultado = incidenteRepository.findAll();

        if (resultado.isEmpty()) {
            result.setError(true);
            result.setMessage("No hay incidentes respecto a los criterios elegidos");
            result.setCode("404");
        }

        return result;
    }
}
