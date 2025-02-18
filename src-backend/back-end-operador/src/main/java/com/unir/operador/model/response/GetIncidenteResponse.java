package com.unir.operador.model.response;

import com.unir.operador.model.pojo.Incidente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIncidenteResponse {
    private boolean error;
    private String code;
    private List<Incidente> incidentes;
    private String message;
}
