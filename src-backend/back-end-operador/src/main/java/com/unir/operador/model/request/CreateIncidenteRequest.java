package com.unir.operador.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIncidenteRequest {

    @NotNull(message = "La descripción no puede ser nula")
    @NotEmpty(message = "La descripción no puede estar vacía")
    private String Descripcion;
}
