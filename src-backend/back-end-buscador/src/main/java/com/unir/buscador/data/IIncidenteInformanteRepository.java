package com.unir.buscador.data;

import com.unir.buscador.model.pojo.IncidenteInformante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IIncidenteInformanteRepository extends JpaRepository<IncidenteInformante,Integer>,
        JpaSpecificationExecutor<IncidenteInformante> {
}
