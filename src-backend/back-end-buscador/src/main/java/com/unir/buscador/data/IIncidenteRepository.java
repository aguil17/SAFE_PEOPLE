package com.unir.buscador.data;

import com.unir.buscador.model.pojo.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IIncidenteRepository extends JpaRepository<Incidente,Integer>, JpaSpecificationExecutor<Incidente>{

}