package com.unir.operador.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "incident_type")
    @NotNull(message = "El tipo incidente no puede ser nulo")
    private IncidentType incidentType;

    @Column(name = "description")
    @NotNull(message = "descripción no puede ser nula")
    private String description;

    @Column(name = "date")
    @NotNull(message = "fecha no puede ser nula")
    private LocalDate date;

    @Column(name = "time")
    @NotNull(message = "hora no puede ser nula")
    private LocalTime time;

    @Column(name = "photo")
    private String photo;


    @Column(name = "deleteAt")
    private Instant deleteAt;

    @Column(name = "creation_date")
    @NotNull(message = "fechaCreacion no puede ser nula")
    private Instant creationDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_location", referencedColumnName = "id", unique = true)
    @JsonIgnoreProperties("incidente") // Evita referencias cíclicas
    private Ubicacion ubicacion;


    @OneToMany(mappedBy = "incidente", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Herido> heridos;


    @OneToMany(mappedBy = "incidente", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Material> materiales;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties("incidentes")
    private Usuario usuario;

    @Override
    public int hashCode() {
        return Objects.hash(id);  // Solo utilizar el campo único
    }

    public Incidente() {

    }
}
