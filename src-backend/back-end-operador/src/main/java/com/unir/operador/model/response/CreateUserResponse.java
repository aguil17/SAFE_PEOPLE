package com.unir.operador.model.response;


import com.unir.operador.model.pojo.Persona;
import com.unir.operador.model.pojo.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {
    private boolean error;
    private String code;
    private Usuario usuario;
    private Persona persona;
    private String message;
}
