package com.unir.buscador.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateIncidenteMaterialRequest {

    @NotNull(message = "El tipo de material no puede ser nulo")
    @NotEmpty(message = "El tipo de material no puede ser vacío")
    private String TipoMaterial;

    @NotNull(message = "Descripción de material no puede ser nulo")
    @NotEmpty(message = "Descripción de material no puede ser vacío")
    private String Descripcion;

    @NotNull(message = "Cantidad de material no puede ser nulo")
    @NotEmpty(message = "Cantidad de material no puede ser vacío")
    private String Cantidad;

    private String CondicionMaterial;
}
