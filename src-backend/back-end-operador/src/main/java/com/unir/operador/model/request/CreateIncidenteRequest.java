package com.unir.operador.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIncidenteRequest {

    @NotNull(message = "El tipo incidente no puede ser nulo")
    @NotEmpty(message = "El tipo incidente no puede ser vacío")
    @Pattern(regexp = "\\d+", message = "El tipo incidente debe contener solo números")
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

    @NotNull(message = "La ubicación no puede ser nula")
    @NotEmpty(message = "La ubicación no puede ser vacía")
    @Pattern(regexp = "\\d+", message = "La ubicación debe contener solo números")
    private String idUbicacion;

    @NotNull(message = "Lista de materiales no puede ser nula")
    @NotEmpty(message = "Lista de materiales no puede ser vacía")
    private CreateIncidenteMaterialRequest[] materiales;

    private CreateIncidenteInformanteRequest[] informantes;

    @NotNull(message = "Ubicación no puede ser nula")
    private CreateLocationRequest Ubicacion;

    private CreateIncidenteHeridoRequest[] Heridos;
}
