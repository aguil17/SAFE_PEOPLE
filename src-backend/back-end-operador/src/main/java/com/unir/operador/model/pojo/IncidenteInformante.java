package com.unir.operador.model.pojo;


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
    private Integer idIncident;

    @Id
    @Column(name = "id_informant")
    private Integer idInformant;

    @Column(name = "assignment_date")
    private Timestamp assignmentDate;

    public IncidenteInformante() {

    }
}
