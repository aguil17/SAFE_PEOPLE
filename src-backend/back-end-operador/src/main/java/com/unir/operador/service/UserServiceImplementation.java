package com.unir.operador.service;

import com.unir.operador.data.IPersonaRepository;
import com.unir.operador.data.IUsuarioRepository;
import com.unir.operador.model.pojo.GeneroType;
import com.unir.operador.model.pojo.Persona;
import com.unir.operador.model.pojo.RoleType;
import com.unir.operador.model.pojo.Usuario;
import com.unir.operador.model.request.CreateUserRequest;
import com.unir.operador.model.response.CreateUserResponse;
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
            response.setError(true);
            response.setCode("409");
            response.setMessage("Ya existe un usuario con ese correo");

            return response;
        }

        var persona = Persona.builder()
                .name(request.getNombre())
                .last_name(request.getApellidos())
                .dni(request.getDni())
                .email(request.getCorreo())
                .cellphone(request.getCelular())
                .birthdate(request.getCumpleanios())
                .gender(genero)
                .creation_date(fechaActual)
                .build();

        var personaEntity = personaRepository.save(persona);

        RoleType role = RoleType.valueOf(request.getRole());

        var usuario = Usuario.builder()
                .username(request.getNombreUsuario())
                .password(request.getPassword())
                .role(role)
                .id_person(personaEntity.getId())
                .creation_date(fechaActual).build();

        usuarioRepository.save(usuario);

        response.setError(false);
        response.setCode("201");
        usuario.setPassword(null);
        response.setUsuario(usuario);
        response.setPersona(persona);

        return response;
    }
}


