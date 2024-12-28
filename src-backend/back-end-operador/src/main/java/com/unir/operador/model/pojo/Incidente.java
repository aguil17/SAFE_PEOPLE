package com.unir.operador.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;


@Data
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Builder
@Table(name = "incident")
public class Incidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @Column(name = "incident_type")
    @NotNull(message = "El tipo incidente no puede ser nulo")
    private Integer incident_type;

    @Column(name = "descripcion")
    @NotNull(message = "descripción no puede ser nula")
    private String descripcion;

    @Column(name = "date")
    @NotNull(message = "fecha no puede ser nula")
    private LocalDate date;

    @Column(name = "time")
    @NotNull(message = "hora no puede ser nula")
    private LocalTime time;

    @Column(name = "photo")
    private String photo;

    @Column(name = "creation_date")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private LocalDate creation_date;

    @Column(name = "id_location")
    @NotNull(message = "Location no puede ser nulo")
    private Integer id_location;

    public Incidente() {

    }
}
