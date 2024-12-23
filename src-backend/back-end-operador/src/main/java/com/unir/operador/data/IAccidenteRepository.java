package com.unir.operador.data;

import com.unir.operador.model.pojo.Accidente;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


public interface IAccidenteRepository extends JpaRepository<Accidente,Integer>, JpaSpecificationExecutor<Accidente>
{

}