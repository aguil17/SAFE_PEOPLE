package com.unir.operador.model.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class IncidenteInformantePK implements Serializable {
    private Integer idIncident;
    private Integer idInformant;
}