package com.unir.buscador.model.response;

import com.unir.buscador.model.pojo.Incidente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIncidenteResponse {
    private boolean error;
    private String code;
    private Incidente[] incidentes;
    private String message;
}

