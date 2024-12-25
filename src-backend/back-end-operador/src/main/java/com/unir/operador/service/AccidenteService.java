package com.unir.operador.service;

import com.unir.operador.model.request.CreateIncidenteRequest;
import com.unir.operador.model.response.CreateIncidenteResponse;

public interface AccidenteService {

    public CreateIncidenteResponse crearAccidente(CreateIncidenteRequest request);
}
