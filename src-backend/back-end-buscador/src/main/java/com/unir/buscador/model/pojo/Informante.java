package com.unir.buscador.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Builder
@Table(name = "informant")
public class Informante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @Column(name = "name")
    @NotNull(message = "name no puede ser nulo")
    private String name;

    @Column(name = "last_name")
    @NotNull(message = "last_name no puede ser nulo")
    private String lastName;

    @Column(name = "cellphone")
    @NotNull(message = "cellphone no puede ser nulo")
    private String cellphone;

    @Column(name = "email")
    @NotNull(message = "email no puede ser nulo")
    private String email;

    @Column(name = "creation_date")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private Instant creationDate;

    public Informante() {

    }
}