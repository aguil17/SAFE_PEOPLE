package com.unir.operador.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateIncidenteBuscadorRequest {

    @JsonProperty("tipoIncidente")
    private String TipoIncidente;

    private String description_incident;

    private String Fecha;

    private String Hora;

    private String Foto;

    private Integer idLocation;

    private String NombreCiudad;

    private String NombreDistrito;

    private String Descripcion;

    private String Referencia;

    private String Latitud;

    private String Longitud;
    
    private int id_user;

    private String username;

    private String password_user;

    private String role;

    private int id_person;

    private String person_name;

    private String person_last_name;

    private String dni;

    private String email;

    private String cellphone;

    private String password_person;

    private String birthdate;

    private String gender;

    private CreateIncidenteMaterialBuscadorRequest[] materiales;

    private CreateIncidenteInformanteBuscadorRequest[] informantes;

    private CreateIncidenteHeridoBuscadorRequest[] Heridos;

}
