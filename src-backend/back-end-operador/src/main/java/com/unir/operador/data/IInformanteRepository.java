package com.unir.operador.data;

import com.unir.operador.model.pojo.Informante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IInformanteRepository extends JpaRepository<Informante,Integer>, JpaSpecificationExecutor<Informante> {
}
