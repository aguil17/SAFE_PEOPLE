package com.unir.operador.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

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
    private String materialType;

    @Column(name = "description")
    @NotNull(message = "description no puede ser nula")
    private String description;

    @Column(name = "quantity")
    @NotNull(message = "quantity no puede ser nula")
    private Integer quantity;

    @Column(name = "material_condition")
    private String materialCondition;

    @Column(name = "creation_date")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private Instant creationDate;

    @ManyToOne
    @JoinColumn(name = "id_incident")
    @JsonBackReference
    private Incidente incidente;

    public Material() {

    }
}
