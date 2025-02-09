package com.unir.buscador.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "incident_informant")
public class IncidenteInformante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Integer id;

    @Column(name = "name")
    @NotNull(message = "name no puede ser nulo")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_incident")
    @JsonBackReference
    private Incidente incidente;

    @Column(name = "assignment_date")
    private Instant assignmentDate;

    @Column(name = "creation_date")
    private Instant creationDate;

    public IncidenteInformante() {

    }
}
