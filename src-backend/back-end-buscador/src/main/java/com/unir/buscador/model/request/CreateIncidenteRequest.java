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
    private String description_incident;

    @NotNull(message = "La fecha no puede ser nula")
    @NotEmpty(message = "La fecha no puede ser vacía")
    private String Fecha;

    @NotNull(message = "La Hora no puede ser nula")
    @NotEmpty(message = "La Hora no puede ser vacía")
    private String Hora;

    private String Foto;

    private Integer idLocation;

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

    @Column(name = "id_user")
    private int id_user;

    @Column(name = "username")
    private String username;

    @Column(name = "password_user")
    private String password_user;

    @Column(name = "role")
    private String role;

    @Column(name = "id_person")
    private int id_person;

    @Column(name = "person_name")
    private String person_name;

    @Column(name = "person_last_name")
    private String person_last_name;

    @Column(name = "dni")
    private String dni;

    @Column(name = "email")
    private String email;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "password_person")
    private String password_person;

    @Column(name = "birthdate")
    private String birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private String gender;

    @NotNull(message = "Lista de materiales no puede ser nula")
    @NotEmpty(message = "Lista de materiales no puede ser vacía")
    private CreateIncidenteMaterialRequest[] materiales;

    private CreateIncidenteInformanteRequest[] informantes;
}
