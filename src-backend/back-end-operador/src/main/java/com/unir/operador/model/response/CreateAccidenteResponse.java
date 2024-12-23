package com.unir.operador.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccidenteResponse {
    private boolean error;
    private String code;
    private String message;
}
