package com.unir.operador.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Column(name = "id_informat")
    private Integer id_informat;

    @Column(name = "assignment_date")
    private Timestamp assignment_date;

    public IncidenteInformante() {

    }
}
