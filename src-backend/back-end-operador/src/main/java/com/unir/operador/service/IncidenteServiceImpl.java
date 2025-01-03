package com.unir.operador.service;
import com.unir.operador.data.*;
import com.unir.operador.facade.IncidentesFacade;
import com.unir.operador.model.request.CreateIncidenteRequest;
import com.unir.operador.model.request.UpdateIncidenteRequest;
import com.unir.operador.model.response.CreateIncidenteResponse;
import com.unir.operador.model.response.DeleteIncidenteResponse;
import com.unir.operador.model.response.UpdateIncidenteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unir.operador.model.pojo.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;

@Service
public class IncidenteServiceImpl implements IncidenteService {

    @Autowired
    private IIncidenteRepository incidenteRepository;

    @Autowired
    private IUbicacionRepository ubicacionRepository;

    @Autowired
    private IMaterialRepository materialRepository;

    @Autowired
    private IInformanteRepository informanteRepository;

    @Autowired
    private IIncidenteInformanteRepository incidenteInformanteRepository;

    @Autowired
    private IHeridoRepository heridoRepository;

    @Autowired
    private IncidentesFacade incidentesFacade;

    public CreateIncidenteResponse crearIncidente(CreateIncidenteRequest request)
    {
        var result = new CreateIncidenteResponse();

        Timestamp fechaActual = Timestamp.from(Instant.now());

        var requestUbicacion = request.getUbicacion();

        Ubicacion ubicacion = Ubicacion.builder().city_name(requestUbicacion.getNombreCiudad())
                .description(requestUbicacion.getDescripcion())
                .district_name(requestUbicacion.getNombreDistrito())
                .reference(requestUbicacion.getReferencia())
                .latitude(requestUbicacion.getLatitud())
                .longitude(requestUbicacion.getLongitud())
                .creation_date(fechaActual)
                .build();

        var ubicacionSaved = ubicacionRepository.save(ubicacion);

        IncidentType incidentType = IncidentType.valueOf(request.getTipoIncidente());

        Incidente incidente = Incidente.builder()
                .incident_type(incidentType)
                .description(request.getDescripcion())
                .date(LocalDate.parse(request.getFecha()))
                .time(LocalTime.parse(request.getHora()))
                .photo(request.getFoto())
                .creation_date(fechaActual)
                .deleteAt(null)
                .id_location(ubicacionSaved.getId()).build();


        var incidenteSaved = incidenteRepository.save(incidente);

        incidentesFacade.RegistrarIncidente(request);

        if (request.getMateriales() != null)
        {
            for (var material : request.getMateriales())
            {
                var materialEntity = Material.builder()
                        .material_type(material.getTipoMaterial())
                        .description(material.getDescripcion())
                        .quantity(Integer.parseInt(material.getCantidad()))
                        .material_condition(material.getCondicionMaterial())
                        .creation_date(fechaActual)
                        .id_incident(incidenteSaved.getId()).build();

                materialRepository.save(materialEntity);
            }
        }

        if (request.getHeridos() != null)
        {
            for (var herido : request.getHeridos())
            {
                GeneroType genero = GeneroType.valueOf(herido.getGenero());

                EstadoSaludType estadoSalud = EstadoSaludType.valueOf(herido.getEstadoSalud());

                EstadoVital estadoVital = EstadoVital.valueOf(herido.getEstadoVital());

                var heridoEntity = Herido.builder()
                        .quantity(Integer.parseInt(herido.getCantidad()))
                        .name(herido.getNombre())
                        .last_name(herido.getApellidos())
                        .wounded_type(herido.getTipoHerido())
                        .age(Integer.parseInt(herido.getEdad()))
                        .gender(genero)
                        .health_status(estadoSalud)
                        .vital_status(estadoVital)
                        .type_enjury(herido.getTipoHerida())
                        .creation_date(fechaActual)
                        .description_enjury(herido.getDescripcionHerida())
                        .id_incident(incidenteSaved.getId())
                        .build();

                heridoRepository.save(heridoEntity);
            }
        }

        if (request.getInformantes() != null)
        {
            for (var informante : request.getInformantes())
            {

                var informanteEntity = informanteRepository.findByEmail(informante.getCorreoElectronico());

                Informante informanteSaved = null;

                if (informanteEntity.isEmpty())
                {
                    var informanteEntidad = Informante.builder()
                            .name(informante.getNombre())
                            .last_name(informante.getApellidos())
                            .cellphone(informante.getCelular())
                            .email(informante.getCorreoElectronico())
                            .creation_date(fechaActual)
                            .build();

                    informanteSaved = informanteRepository.save(informanteEntidad);
                }
                else
                {
                    informanteSaved = informanteEntity.get();
                }

                var incidenteInformante = IncidenteInformante.builder()
                        .id_incident(incidenteSaved.getId())
                        .id_informant(informanteSaved.getId())
                        .assignment_date(fechaActual)
                        .build();

                incidenteInformanteRepository.save(incidenteInformante);
            }
        }

        result.setError(false);
        result.setData(incidenteSaved);
        result.setCode("201");
        return result;
    }

    public UpdateIncidenteResponse actualizarIncidente(String incidenteId,UpdateIncidenteRequest request)
    {
        var result = new UpdateIncidenteResponse();

        var incidente = incidenteRepository.getById(Integer.parseInt(incidenteId));

        if (incidente == null)
        {
            result.setError(true);
            result.setCode("404");
            result.setMessage("El incidente no existe");
            return result;
        }

        return new UpdateIncidenteResponse();
    }

    public DeleteIncidenteResponse eliminarIncidente(String incidenteId)
    {
        var result = new DeleteIncidenteResponse();
        var incidente = incidenteRepository.findByIdAndDeleteAtIsNull(Integer.parseInt(incidenteId));

        if (incidente.isEmpty())
        {
            result.setError(true);
            result.setCode("404");
            result.setMessage("El incidente no existe");
            return result;
        }

        var fechaActual = Timestamp.from(Instant.now());

        var incidenteToDelete = incidente.get();
        incidenteToDelete.setDeleteAt(fechaActual);

        incidenteRepository.save(incidenteToDelete);

        result.setError(false);
        result.setData(incidenteToDelete);
        result.setCode("200");
        return result;
    }
}
