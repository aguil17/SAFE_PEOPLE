package com.unir.operador.data;

import com.unir.operador.model.pojo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUsuarioRepository extends JpaRepository<Usuario,Integer>, JpaSpecificationExecutor<Usuario> {
}
