package com.unir.operador.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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
    @JsonProperty
    @Column(name = "id_incident")
    private Integer id_incident;

    @Column(name = "id_informat")
    private Integer id_informat;

    public IncidenteInformante() {

    }
}
