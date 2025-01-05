package com.unir.buscador.data;

import com.unir.buscador.model.pojo.Incidente;
import com.unir.buscador.model.pojo.Informante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IInformanteRepository extends JpaRepository<Informante,Integer>, JpaSpecificationExecutor<Informante> {

    public Optional<Informante> findByEmail(String email);
}
