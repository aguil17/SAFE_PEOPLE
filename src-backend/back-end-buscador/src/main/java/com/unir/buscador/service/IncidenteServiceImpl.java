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


        return new GetIncidenteResponse();
    }
}

