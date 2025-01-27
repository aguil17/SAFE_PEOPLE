package com.unir.operador.data;

import com.unir.operador.model.pojo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IPersonaRepository extends JpaRepository<Persona,Integer>, JpaSpecificationExecutor<Persona> {

    Optional<Persona> findByEmail(String email);

}
