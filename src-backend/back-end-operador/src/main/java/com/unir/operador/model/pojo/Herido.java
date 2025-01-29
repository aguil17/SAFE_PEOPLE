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
    private String woundedType;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GeneroType gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_status")
    private EstadoSaludType healthStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "vital_status")
    private EstadoVital vitalStatus;

    @Column(name = "creation_date")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private Timestamp creationDate;

    @Column(name = "type_enjury")
    private String typeEnjury;

    @Column(name = "description_enjury")
    private String descriptionEnjury;

    @Column(name = "id_incident")
    private Integer idIncident;

    public Herido() {

    }
}
