package com.unir.operador.data;

import com.unir.operador.model.pojo.Incidente;
import com.unir.operador.model.pojo.IncidenteInformante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IIncidenteInformanteRepository extends JpaRepository<IncidenteInformante,Integer>, JpaSpecificationExecutor<IncidenteInformante> {
}
