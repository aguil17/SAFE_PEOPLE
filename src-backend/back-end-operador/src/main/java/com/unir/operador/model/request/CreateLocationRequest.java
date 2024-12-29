package com.unir.operador.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateLocationRequest {

    @NotNull(message = "Nombre de ciudad no puede ser nula")
    @NotEmpty(message = "Nombre de ciudad no puede ser vacía")
    private String NombreCiudad;

    @NotNull(message = "Distrito no puede ser nulo")
    @NotEmpty(message = "Distrito no puede ser vacío")
    private String NombreDistrito;

    @NotNull(message = "Descripción no puede ser nulo")
    @NotEmpty(message = "Descripción no puede ser vacío")
    private String Descripcion;

    private String Referencia;

    @NotNull(message = "Latitud no puede ser nulo")
    @NotEmpty(message = "Latitud no puede ser vacío")
    private String Latitud;

    @NotNull(message = "Longitud no puede ser nulo")
    @NotEmpty(message = "Longitud no puede ser vacío")
    private String Longitud;
}
