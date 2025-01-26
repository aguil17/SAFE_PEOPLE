package com.unir.operador.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotNull(message = "Nombre no puede ser nulo")
    @NotEmpty(message = "Nombre no puede ser vacío")
    private String Nombre;

    @NotNull(message = "Apellidos no puede ser nulo")
    @NotEmpty(message = "Apellidos no puede ser vacío")
    private String Apellidos;

    @NotNull(message = "Dni no puede ser nulo")
    @NotEmpty(message = "Dni no puede ser vacío")
    private String Dni;

    @NotNull(message = "Correo no puede ser nulo")
    @NotEmpty(message = "Correo no puede ser vacío")
    private String Correo;

    @NotNull(message = "Celular no puede ser nulo")
    @NotEmpty(message = "Celular no puede ser vacío")
    private String Celular;

    @NotNull(message = "Cumpleanios no puede ser nulo")
    @NotEmpty(message = "Cumpleanios no puede ser vacío")
    private String Cumpleanios;

    @NotNull(message = "Genero no puede ser nulo")
    @NotEmpty(message = "Genero no puede ser vacío")
    private String Genero;

    @NotNull(message = "NombreUsuario no puede ser nulo")
    @NotEmpty(message = "NombreUsuario no puede ser vacío")
    private String NombreUsuario;

    @NotNull(message = "Password no puede ser nulo")
    @NotEmpty(message = "Password no puede ser vacío")
    private String Password;

    @NotNull(message = "Role no puede ser nulo")
    @NotEmpty(message = "Role no puede ser vacío")
    private String Role;
}

