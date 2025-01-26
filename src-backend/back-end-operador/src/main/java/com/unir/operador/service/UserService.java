package com.unir.operador.service;

import com.unir.operador.model.request.CreateUserRequest;
import com.unir.operador.model.response.CreateUserResponse;

public interface UserService {

    CreateUserResponse crearUser(CreateUserRequest request);
}
