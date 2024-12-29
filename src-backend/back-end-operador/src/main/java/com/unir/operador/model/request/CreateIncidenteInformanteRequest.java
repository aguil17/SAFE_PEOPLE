package com.unir.operador.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

public class CreateIncidenteInformanteRequest {

    @NotNull(message = "Nombre del informante no puede ser nulo")
    @NotEmpty(message = "Nombre del informante no puede ser vacío")
    private String Nombre;

    @NotNull(message = "Apellidos del informante no puede ser nulo")
    @NotEmpty(message = "Apellidos del informante no puede ser vacío")
    private String Apellidos;

    @NotNull(message = "Celular del informante no puede ser nulo")
    @NotEmpty(message = "Celular del informante no puede ser vacío")
    private String Celular;

    private String CorreoElectronico;
}
