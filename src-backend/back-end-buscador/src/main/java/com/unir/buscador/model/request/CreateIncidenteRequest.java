package com.unir.buscador.model.request;

import com.unir.buscador.model.pojo.GenderType;
import com.unir.buscador.model.pojo.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIncidenteRequest {

    @NotNull(message = "El tipo incidente no puede ser nulo")
    @NotEmpty(message = "El tipo incidente no puede estar vacío")
    private String TipoIncidente;

    @NotNull(message = "La descripción del incident no puede ser nula")
    @NotEmpty(message = "La descripción no puede estar vacía")
    private String Description_incident;

    @NotNull(message = "La fecha no puede ser nula")
    @NotEmpty(message = "La fecha no puede ser vacía")
    private String Fecha;

    @NotNull(message = "La Hora no puede ser nula")
    @NotEmpty(message = "La Hora no puede ser vacía")
    private String Hora;

    private String Foto;

    private Integer IdLocation;

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

    private int Id_user;

    private String Username;

    private String Password_user;

    private String Role;

    private int Id_person;

    private String Person_name;

    private String Person_last_name;

    private String Dni;

    private String Email;

    private String Cellphone;

    private String Password_person;

    private String Birthdate;

    private String Gender;

    @NotNull(message = "Lista de materiales no puede ser nula")
    @NotEmpty(message = "Lista de materiales no puede ser vacía")
    private CreateIncidenteMaterialRequest[] Materiales;

    private CreateIncidenteInformanteRequest[] Informantes;

    private CreateIncidenteHeridoRequest[] Heridos;
}
