package com.unir.operador.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateIncidenteHeridoBuscadorRequest {

    private String Cantidad;

    private String Nombre;

    private String Apellidos;

    private String Edad;

    private String Genero;

    private String TipoHerido;

    private String EstadoVital;

    private String EstadoSalud;

    private String TipoHerida;

    private String DescripcionHerida;
}
