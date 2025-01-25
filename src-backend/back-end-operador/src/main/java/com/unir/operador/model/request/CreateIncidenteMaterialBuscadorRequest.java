package com.unir.operador.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateIncidenteMaterialBuscadorRequest {

    private String TipoMaterial;

    private String Descripcion;

    private String Cantidad;

    private String CondicionMaterial;
}
