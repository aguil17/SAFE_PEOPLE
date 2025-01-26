package com.unir.operador.controller;

import com.unir.operador.model.request.CreateUserRequest;
import com.unir.operador.model.response.CreateUserResponse;
import com.unir.operador.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "User Controller", description = "Gestión del User")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    @Operation(
            operationId = "user-post",
            description = "Operación de registro de user.",
            summary = "Operación de registro de user.")
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = CreateUserResponse.class)))
    @ApiResponse(
            responseCode = "409",
            description = "Ya existe un usuario con ese correo",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUserResponse.class)))
    public ResponseEntity<CreateUserResponse> crearUser(@Valid @RequestBody CreateUserRequest request)
    {
        var response = userService.crearUser(request);

        if (response.isError() && response.getCode() == "409")
        {
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
