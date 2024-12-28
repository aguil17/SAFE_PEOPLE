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
@Table(name = "location")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @Column(name = "city_name")
    @NotNull(message = "nombre de ciudad no puede ser nula")
    private String city_name;

    @Column(name = "district_name")
    @NotNull(message = "nombre de distrito no puede ser nulo")
    private  String district_name;

    @Column(name = "descripcion")
    @NotNull(message = "descripcion no puede ser nulo")
    private String descripcion;

    @Column(name = "reference")
    @NotNull(message = "referencia no puede ser nula")
    private String reference;

    @Column(name = "latitude")
    @NotNull(message = "latitud no puede ser nula")
    private String latitude;

    @Column(name = "longitude")
    @NotNull(message = "longitud no puede ser nula")
    private String longitude;

    @Column(name = "creation_date")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private LocalDate creation_date;


    public Ubicacion () {

    }
}
