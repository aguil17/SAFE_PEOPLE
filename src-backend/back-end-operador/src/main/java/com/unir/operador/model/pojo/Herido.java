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
@Table(name = "wounded")
public class Herido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "wounded_type")
    private String wounded_type;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GeneroType gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_status")
    private EstadoSaludType health_status;

    @Enumerated(EnumType.STRING)
    @Column(name = "vital_status")
    private EstadoVital vital_status;

    @Column(name = "creation_date")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private Timestamp creation_date;

    @Column(name = "type_enjury")
    private String type_enjury;

    @Column(name = "description_enjury")
    private String description_enjury;

    @Column(name = "id_incident")
    private Integer id_incident;

    public Herido() {

    }
}
