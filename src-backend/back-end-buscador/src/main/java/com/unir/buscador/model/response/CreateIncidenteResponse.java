package com.unir.buscador.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIncidenteResponse {
    private boolean error;
    private String code;
    private String message;
}
