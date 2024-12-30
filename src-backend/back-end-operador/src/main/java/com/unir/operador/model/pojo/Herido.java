package com.unir.operador.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

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
    private String last_name;

    @Column(name = "wounded_type")
    private String wounded_type;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "health_status")
    private String health_status;

    @Column(name = "vital_status")
    private String vital_status;

    @Column(name = "creation_date")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private LocalDate creation_date;

    @Column(name = "type_enjury")
    private String type_enjury;

    @Column(name = "description_enjury")
    private String description_enjury;

    public Herido() {

    }
}
