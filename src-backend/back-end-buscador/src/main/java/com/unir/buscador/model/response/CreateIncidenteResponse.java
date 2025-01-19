package com.unir.buscador.model.response;

import com.unir.buscador.model.pojo.Incidente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIncidenteResponse {
    private boolean error;
    private String code;
    private Incidente data;
    private String message;
}
