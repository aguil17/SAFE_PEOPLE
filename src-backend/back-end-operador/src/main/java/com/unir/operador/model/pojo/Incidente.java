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
@Table(name = "incidente")
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

    @Column(name = "fecha")
    @NotNull(message = "fecha no puede ser nula")
    private LocalDate fecha;

    @Column(name = "hora")
    @NotNull(message = "hora no puede ser nula")
    private LocalTime hora;

    @Column(name = "foto")
    private String foto;

    @Column(name = "fechaCreacion")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private LocalDate fechaCreacion;

    @Column(name = "idLocation")
    @NotNull(message = "Location no puede ser nulo")
    private Integer idLocation;

    public Incidente () {

    }
}
