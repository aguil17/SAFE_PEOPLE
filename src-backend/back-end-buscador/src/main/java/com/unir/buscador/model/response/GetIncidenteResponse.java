package com.unir.buscador.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIncidenteResponse {
    private boolean error;
    private String code;
    private String message;
}
