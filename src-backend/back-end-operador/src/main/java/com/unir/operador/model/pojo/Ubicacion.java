package com.unir.operador.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;


import jakarta.persistence.Entity;
import lombok.*;

@Data
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Builder
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    private String nombreCiudad;

    private  String nombreDistrito;

    private String descripcion;

    private String referencia;

    private String latitud;

    private String longitud;

    @Column(name = "fechaCreacion")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private LocalDate fechaCreacion;


    public Ubicacion () {

    }
}
