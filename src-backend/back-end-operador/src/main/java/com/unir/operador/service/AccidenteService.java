package com.unir.operador.service;

import com.unir.operador.model.request.CreateAccidenteRequest;
import com.unir.operador.model.response.CreateAccidenteResponse;

public interface AccidenteService {

    public CreateAccidenteResponse crearAccidente(CreateAccidenteRequest request);
}
