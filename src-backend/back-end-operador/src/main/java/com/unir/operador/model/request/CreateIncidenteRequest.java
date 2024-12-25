package com.unir.operador.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIncidenteRequest {

    @NotNull(message = "El tipo incidente no puede ser nulo")
    @NotEmpty(message = "El tipo incidente no puede estar vacío")
    private Integer TipoIncidente;

    @NotNull(message = "La descripción no puede ser nula")
    @NotEmpty(message = "La descripción no puede estar vacía")
    private String Descripcion;

    @NotNull(message = "La fecha no puede ser nula")
    @NotEmpty(message = "La fecha no puede estar vacía")
    private LocalDate Fecha;

    @NotNull(message = "La fecha no puede ser nula")
    @NotEmpty(message = "La fecha no puede estar vacía")
    private LocalTime Hora;


    private String Foto;

    @NotNull(message = "La idLocation no puede ser nula")
    @NotEmpty(message = "La foto no puede estar vacía")
    private Integer idLocation;
}
