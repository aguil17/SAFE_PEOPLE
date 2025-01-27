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

    @NotNull(message = "El tipo incidente no puede ser nulo")
    @NotEmpty(message = "El tipo incidente no puede estar vacío")
    private String TipoIncidente;

    @NotNull(message = "La descripción no puede ser nula")
    @NotEmpty(message = "La descripción no puede estar vacía")
    private String Descripcion;

    @NotNull(message = "La fecha no puede ser nula")
    @NotEmpty(message = "La fecha no puede ser vacía")
    private String Fecha;

    @NotNull(message = "La Hora no puede ser nula")
    @NotEmpty(message = "La Hora no puede ser vacía")
    private String Hora;

    private String Foto;

    @NotNull(message = "Lista de materiales no puede ser nula")
    @NotEmpty(message = "Lista de materiales no puede ser vacía")
    private CreateIncidenteMaterialRequest[] Materiales;

    private CreateIncidenteInformanteRequest[] Informantes;

    @NotNull(message = "Ubicación no puede ser nula")
    private CreateLocationRequest Ubicacion;

    private CreateIncidenteHeridoRequest[] Heridos;

    private String IdUsuario;
}

