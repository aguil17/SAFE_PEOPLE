package com.unir.operador.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Builder
@Table(name = "person")
public class Persona {

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
    private String last_name;

    @Column(name = "dni")
    @NotNull(message = "dni no puede ser nulo")
    private String dni;

    @Column(name = "email")
    @NotNull(message = "email no puede ser nulo")
    private String email;

    @Column(name = "cellphone")
    @NotNull(message = "cellphone no puede ser nulo")
    private String cellphone;

    @Column(name = "birthdate")
    @NotNull(message = "birthdate no puede ser nulo")
    private String birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    @NotNull(message = "gender no puede ser nulo")
    private GeneroType gender;

    @Column(name = "creation_date")
    private Timestamp creation_date;

    @Column(name = "update_date")
    private Timestamp update_date;

    public Persona() {

    }
}
