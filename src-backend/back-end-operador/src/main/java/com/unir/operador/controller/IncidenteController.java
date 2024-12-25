package com.unir.operador.controller;

import com.unir.operador.model.request.CreateIncidenteRequest;
import com.unir.operador.model.response.CreateIncidenteResponse;
import com.unir.operador.service.AccidenteService;
import com.unir.operador.util.ResponseMessage;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/accidente")
//@RequiredArgsConstructor
@Slf4j
@Tag(name = "Incidente Controller", description = "Gestión del Incidente")
public class IncidenteController {

    private final AccidenteService accidenteService;

    public IncidenteController(AccidenteService accidenteService) {
        this.accidenteService = accidenteService;
    }

    @GetMapping
    @Operation(
            operationId = "categories-get",
            description = "Operacion de lectura para devolver las categorias",
            summary = "Se devuelve una lista de las categorias almacenadas en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = ResponseMessage.EJEMPLO_NOT_FOUND)
    public ResponseEntity<CreateIncidenteResponse> getCategorys() {

        var response = new CreateIncidenteResponse();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    @Operation(
            operationId = "incidente-post",
            description = "Operación de registro de incidente",
            summary = "Se registra un accidente de tránsito, incendio o robo.")
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateIncidenteResponse.class)))
    public ResponseEntity<CreateIncidenteResponse> crearIncidente(@Valid @RequestBody CreateIncidenteRequest request)
    {
        var serviceResult = accidenteService.crearAccidente(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResult);
    }

}