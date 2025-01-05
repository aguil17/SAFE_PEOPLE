package com.unir.buscador.model.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@Builder
@IdClass(IncidenteInformantePK.class)
@Table(name = "incident_informant")
public class IncidenteInformante {

    @Id
    @Column(name = "id_incident")
    private Integer id_incident;

    @Id
    @Column(name = "id_informant")
    private Integer id_informant;

    @Column(name = "assignment_date")
    private Timestamp assignment_date;

    public IncidenteInformante() {

    }
}
