package com.unir.operador.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateIncidenteInformanteBuscadorRequest {

    private String Nombre;

    private String Apellidos;

    private String Celular;

    private String CorreoElectronico;
}
