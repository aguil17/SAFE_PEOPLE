package com.unir.operador.service;

import com.unir.operador.data.IPersonaRepository;
import com.unir.operador.data.IUsuarioRepository;
import com.unir.operador.model.pojo.GeneroType;
import com.unir.operador.model.pojo.Persona;
import com.unir.operador.model.pojo.RoleType;
import com.unir.operador.model.pojo.Usuario;
import com.unir.operador.model.request.CreateUserRequest;
import com.unir.operador.model.response.CreateUserResponse;
import com.unir.operador.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private IPersonaRepository personaRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public CreateUserResponse crearUser(CreateUserRequest request)
    {
        CreateUserResponse response = new CreateUserResponse();

        Timestamp fechaActual = Timestamp.from(Instant.now());

        GeneroType genero = GeneroType.valueOf(request.getGenero());

        var personaPorEmail = personaRepository.findByEmail(request.getCorreo());

        if (personaPorEmail.isPresent())
        {
            var persona = personaPorEmail.get();

            var usuario = usuarioRepository.findByPersona_Id(persona.getId());

            response.setError(true);
            response.setCode("409");
            response.setPersona(persona);

            if (usuario.isPresent())
            {
                response.setUsuario(usuario.get());
            }

            response.setMessage(ResponseMessage.USUARIO_ALREADY_EXISTS);

            return response;
        }

        var persona = Persona.builder()
                .name(request.getNombre())
                .lastName(request.getApellidos())
                .dni(request.getDni())
                .email(request.getCorreo())
                .cellphone(request.getCelular())
                .birthdate(request.getCumpleanios())
                .gender(genero)
                .creationDate(fechaActual)
                .build();

        var personaEntity = personaRepository.save(persona);

        RoleType role = RoleType.valueOf(request.getRole());

        var usuario = Usuario.builder()
                .username(request.getNombreUsuario())
                .password(request.getPassword())
                .role(role)
                .persona(personaEntity)
                .creationDate(fechaActual).build();

        usuarioRepository.save(usuario);

        response.setError(false);
        response.setCode("201");
        usuario.setPassword(null);
        response.setUsuario(usuario);
        response.setPersona(persona);

        return response;
    }
}


