package com.unir.operador.model.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelResult {
    private boolean error;
    private String code;
    private String message;
}
