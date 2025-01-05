package com.unir.buscador.data;

import com.unir.buscador.model.pojo.Informante;
import com.unir.buscador.model.pojo.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IMaterialRepository extends JpaRepository<Material,Integer>, JpaSpecificationExecutor<Material> {
}
