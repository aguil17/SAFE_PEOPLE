package com.unir.operador.service;

import com.unir.operador.model.request.CreateIncidenteRequest;
import com.unir.operador.model.request.UpdateIncidenteRequest;
import com.unir.operador.model.response.CreateIncidenteResponse;
import com.unir.operador.model.response.DeleteIncidenteResponse;
import com.unir.operador.model.response.UpdateIncidenteResponse;

public interface IncidenteService {

    public CreateIncidenteResponse crearIncidente(CreateIncidenteRequest request);
    public UpdateIncidenteResponse actualizarIncidente(String incidenteId,UpdateIncidenteRequest request);
    public DeleteIncidenteResponse eliminarIncidente(String incidenteId);
}
