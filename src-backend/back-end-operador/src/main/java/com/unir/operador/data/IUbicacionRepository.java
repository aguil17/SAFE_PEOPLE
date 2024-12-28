package com.unir.operador.data;

import com.unir.operador.model.pojo.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface IUbicacionRepository extends JpaRepository<Ubicacion,Integer>, JpaSpecificationExecutor<Ubicacion>
{
}
