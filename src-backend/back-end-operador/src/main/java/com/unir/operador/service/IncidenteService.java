package com.unir.operador.service;

import com.unir.operador.model.request.CreateIncidenteRequest;
import com.unir.operador.model.response.CreateIncidenteResponse;
import com.unir.operador.model.response.DeleteIncidenteResponse;
import com.unir.operador.model.response.GetIncidenteResponse;

public interface IncidenteService {

    public CreateIncidenteResponse crearIncidente(CreateIncidenteRequest request);
    public DeleteIncidenteResponse eliminarIncidente(String incidenteId);
    GetIncidenteResponse getIncidentes(String fechaCreacionInicial, String fechaCreacionFinal);
}
