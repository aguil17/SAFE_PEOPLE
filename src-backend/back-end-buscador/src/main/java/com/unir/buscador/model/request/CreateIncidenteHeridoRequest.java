package com.unir.buscador.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIncidenteHeridoRequest {
    private String Cantidad;

    private String Nombre;

    private String Apellidos;

    private String TipoHerido;

    private String Edad;

    private String Genero;

    private String EstadoSalud;

    private String EstadoVital;

    private String TipoHerida;

    private String DescripcionHerida;
}