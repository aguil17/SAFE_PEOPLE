package com.unir.operador.model.response;

import com.unir.operador.model.pojo.incident;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteIncidenteResponse {
    private boolean error;
    private String code;
    private incident data;
    private String message;
}
