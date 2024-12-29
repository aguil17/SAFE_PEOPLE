package com.unir.operador.data;

import com.unir.operador.model.pojo.Herido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IHeridoRepository extends JpaRepository<Herido,Integer>, JpaSpecificationExecutor<Herido> {
}
