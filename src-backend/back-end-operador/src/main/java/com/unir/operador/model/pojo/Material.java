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
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @Column(name = "material_type")
    @NotNull(message = "material_type no puede ser nula")
    private String material_type;

    @Column(name = "description")
    @NotNull(message = "description no puede ser nula")
    private String description;

    @Column(name = "quantity")
    @NotNull(message = "quantity no puede ser nula")
    private Integer quantity;

    @Column(name = "material_condition")
    private String material_condition;

    @Column(name = "creation_date")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private LocalDate creation_date;

    public Material() {

    }
}
