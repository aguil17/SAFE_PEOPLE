package com.unir.buscador.data;

import com.unir.buscador.model.pojo.Herido;
import com.unir.buscador.model.pojo.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IHeridoRepository extends JpaRepository<Herido,Integer>, JpaSpecificationExecutor<Herido> {
}
