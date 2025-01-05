package com.unir.buscador.service;

import com.unir.buscador.model.request.CreateIncidenteRequest;
import com.unir.buscador.model.response.CreateIncidenteResponse;
import com.unir.buscador.model.response.GetIncidenteResponse;

public interface IIncidenteService {

    public GetIncidenteResponse getIncidentes();

    CreateIncidenteResponse createIncidente(CreateIncidenteRequest request);
}
