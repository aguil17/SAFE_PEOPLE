package com.unir.operador.model.request;


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
