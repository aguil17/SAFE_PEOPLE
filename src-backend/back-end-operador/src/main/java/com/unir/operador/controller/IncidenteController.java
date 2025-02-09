package com.unir.operador.controller;

import com.unir.operador.model.request.CreateIncidenteRequest;
import com.unir.operador.model.response.CreateIncidenteResponse;
import com.unir.operador.model.response.DeleteIncidenteResponse;
import com.unir.operador.service.IncidenteService;
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
@RequestMapping("/incidente")
//@RequiredArgsConstructor
@Slf4j
@Tag(name = "Incidente Controller", description = "Gestión del Incidente")
public class IncidenteController {

    private final IncidenteService accidenteService;

    public IncidenteController(IncidenteService accidenteService) {
        this.accidenteService = accidenteService;
    }


    @PostMapping
    @Operation(
            operationId = "incidente-post",
            description = "Operación de registro de incidente",
            summary = "Se registra un accidente de tránsito, incendio o robo.")
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateIncidenteResponse.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateIncidenteResponse.class)),
            description = ResponseMessage.USUARIO_NOT_FOUND)
    public ResponseEntity<CreateIncidenteResponse> crearIncidente(@Valid @RequestBody CreateIncidenteRequest request)
    {
        var serviceResult = accidenteService.crearIncidente(request);

        if (serviceResult.isError() && serviceResult.getCode() == "404")
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(serviceResult);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResult);
    }



    @DeleteMapping("/{incidenteId}")
    @Operation(
            operationId = "incidente-delete",
            description = "Operación de eliminación de incidente",
            summary = "Se elimina un accidente de tránsito, incendio o robo.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteIncidenteResponse.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteIncidenteResponse.class)))
    public ResponseEntity<DeleteIncidenteResponse> eliminarIncidente(@PathVariable String incidenteId)
    {
        var serviceResult = accidenteService.eliminarIncidente(incidenteId);

        if (serviceResult.isError() && serviceResult.getCode() == "404")
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(serviceResult);
        }

        return ResponseEntity.status(HttpStatus.OK).body(serviceResult);
    }
}