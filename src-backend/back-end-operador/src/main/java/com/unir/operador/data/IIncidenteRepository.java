package com.unir.operador.data;

import com.unir.operador.model.pojo.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface IIncidenteRepository extends JpaRepository<Incidente,Integer>, JpaSpecificationExecutor<Incidente>
{
    Optional<Incidente> findByIdAndDeleteAtIsNull(Integer id);
}