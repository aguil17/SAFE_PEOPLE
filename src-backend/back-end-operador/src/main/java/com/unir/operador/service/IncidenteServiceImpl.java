package com.unir.operador.service;
import com.unir.operador.data.*;
import com.unir.operador.facade.IncidentesFacade;
import com.unir.operador.model.request.*;
import com.unir.operador.model.response.CreateIncidenteResponse;
import com.unir.operador.model.response.DeleteIncidenteResponse;
import com.unir.operador.model.response.GetIncidenteResponse;
import com.unir.operador.util.ResponseMessage;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unir.operador.model.pojo.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
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

    @Autowired
    private IUsuarioRepository usuarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(IncidenteServiceImpl.class);

    public CreateIncidenteResponse crearIncidente(CreateIncidenteRequest request)
    {
        var result = new CreateIncidenteResponse();

        var fechaActual = Instant.now();

        var requestUbicacion = request.getUbicacion();

        Ubicacion ubicacion = Ubicacion.builder()
                .cityName(requestUbicacion.getNombreCiudad())
                .description(requestUbicacion.getDescripcion())
                .districtName(requestUbicacion.getNombreDistrito())
                .reference(requestUbicacion.getReferencia())
                .latitude(requestUbicacion.getLatitud())
                .longitude(requestUbicacion.getLongitud())
                .creationDate(fechaActual)
                .build();

        var ubicacionSaved = ubicacionRepository.save(ubicacion);

        IncidentType incidentType = IncidentType.valueOf(request.getTipoIncidente());

        Integer idUsuario = null;
        Optional<Usuario> usuario = Optional.empty();
        if (request.getIdUsuario() != null)
        {
            idUsuario = Integer.parseInt(request.getIdUsuario());

            usuario = usuarioRepository.findById(idUsuario);

            if (usuario.isEmpty())
            {
                result.setError(true);
                result.setCode("404");
                result.setMessage(ResponseMessage.USUARIO_NOT_FOUND);

                return result;
            }
        }

        Usuario newUsuario = null;
        if (usuario.isPresent())
        {
            newUsuario = usuario.get();
        }

        Incidente incidente = Incidente.builder()
                .incidentType(incidentType)
                .description(request.getDescripcion())
                .date(LocalDate.parse(request.getFecha()))
                .time(LocalTime.parse(request.getHora()))
                .photo(request.getFoto())
                .creationDate(fechaActual)
                .deleteAt(null)
                .usuario(newUsuario)
                .ubicacion(ubicacionSaved).build();


        var incidenteSaved = incidenteRepository.save(incidente);

        if (request.getMateriales() != null)
        {
            for (var material : request.getMateriales())
            {
                var materialEntity = Material.builder()
                        .materialType(material.getTipoMaterial())
                        .description(material.getDescripcion())
                        .quantity(Integer.parseInt(material.getCantidad()))
                        .materialCondition(material.getCondicionMaterial())
                        .creationDate(fechaActual)
                        .incidente(incidenteSaved).build();

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
                        .lastName(herido.getApellidos())
                        .woundedType(herido.getTipoHerido())
                        .age(Integer.parseInt(herido.getEdad()))
                        .gender(genero)
                        .healthStatus(estadoSalud)
                        .vitalStatus(estadoVital)
                        .typeEnjury(herido.getTipoHerida())
                        .creationDate(fechaActual)
                        .descriptionEnjury(herido.getDescripcionHerida())
                        .incidente(incidenteSaved)
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
                            .lastName(informante.getApellidos())
                            .cellphone(informante.getCelular())
                            .email(informante.getCorreoElectronico())
                            .creationDate(fechaActual)
                            .build();

                    informanteSaved = informanteRepository.save(informanteEntidad);
                }
                else
                {
                    informanteSaved = informanteEntity.get();
                }

                var incidenteInformante = IncidenteInformante.builder()
                        .idIncident(incidenteSaved.getId())
                        .idInformant(informanteSaved.getId())
                        .assignmentDate(fechaActual)
                        .build();

                incidenteInformanteRepository.save(incidenteInformante);
            }
        }

        var createIncidenteMaterialBuscadorRequestList = new ArrayList<CreateIncidenteMaterialBuscadorRequest>();

        if (request.getMateriales() != null) {
            for(var material : request.getMateriales())
            {
                var createIncidenteMaterialBuscadorRequest = CreateIncidenteMaterialBuscadorRequest.builder()
                        .TipoMaterial(material.getTipoMaterial())
                        .Descripcion(material.getDescripcion())
                        .Cantidad(material.getCantidad())
                        .CondicionMaterial(material.getCondicionMaterial()).build();

                createIncidenteMaterialBuscadorRequestList.add(createIncidenteMaterialBuscadorRequest);
            }
        }


        CreateIncidenteMaterialBuscadorRequest[] materialesArray =
                createIncidenteMaterialBuscadorRequestList.toArray(new CreateIncidenteMaterialBuscadorRequest[0]);

        var createIncidenteHeridoBuscadorRequestList = new ArrayList<CreateIncidenteHeridoBuscadorRequest>();

        if (request.getHeridos() != null)
        {
            for (var herido : request.getHeridos())
            {
                var createIncidenteHeridoBuscadorRequest = CreateIncidenteHeridoBuscadorRequest.builder()
                        .Cantidad(herido.getCantidad())
                        .Nombre(herido.getNombre())
                        .Apellidos(herido.getApellidos())
                        .TipoHerido(herido.getTipoHerido())
                        .Edad(herido.getEdad())
                        .Genero(herido.getGenero())
                        .EstadoSalud(herido.getEstadoSalud())
                        .EstadoVital(herido.getEstadoVital())
                        .TipoHerida(herido.getTipoHerida())
                        .DescripcionHerida(herido.getDescripcionHerida())
                        .build();

                createIncidenteHeridoBuscadorRequestList.add(createIncidenteHeridoBuscadorRequest);
            }
        }

        CreateIncidenteHeridoBuscadorRequest[] heridosArray =
                createIncidenteHeridoBuscadorRequestList.toArray(new CreateIncidenteHeridoBuscadorRequest[0]);

        var createIncidenteInformanteBuscadorRequestList = new ArrayList<CreateIncidenteInformanteBuscadorRequest>();

        if (request.getInformantes() != null)
        {
            for (var informante : request.getInformantes())
            {
                var createIncidenteInformanteBuscadorRequest =
                        CreateIncidenteInformanteBuscadorRequest.builder()
                                .Nombre(informante.getNombre())
                                .Apellidos(informante.getApellidos())
                                .Celular(informante.getCelular())
                                .CorreoElectronico(informante.getCorreoElectronico()).build();

                createIncidenteInformanteBuscadorRequestList.add(createIncidenteInformanteBuscadorRequest);
            }
        }

        CreateIncidenteInformanteBuscadorRequest[] informantesArray =
                createIncidenteInformanteBuscadorRequestList.toArray(new CreateIncidenteInformanteBuscadorRequest[0]);

        var createIncidenteBuscadorRequest = CreateIncidenteBuscadorRequest.builder()
                .TipoIncidente(request.getTipoIncidente())
                .description_incident(request.getDescripcion())
                .Fecha(request.getFecha())
                .Hora(request.getHora())
                .Foto(request.getFoto())
                .idLocation(ubicacionSaved.getId())
                .NombreCiudad(requestUbicacion.getNombreCiudad())
                .NombreDistrito(requestUbicacion.getNombreDistrito())
                .Descripcion(requestUbicacion.getDescripcion())
                .Referencia(requestUbicacion.getReferencia())
                .Latitud(requestUbicacion.getLatitud())
                .Longitud(requestUbicacion.getLongitud())
                .materiales(materialesArray)
                .Heridos(heridosArray)
                .informantes(informantesArray)
                .build();

        incidentesFacade.RegistrarIncidente(createIncidenteBuscadorRequest);

        result.setError(false);
        result.setData(incidenteSaved);
        result.setCode("201");

        logger.info("El incidente fue registrado correctamente. Incidente Id:",incidenteSaved.getId());

        return result;
    }

    public DeleteIncidenteResponse eliminarIncidente(String incidenteId)
    {
        var result = new DeleteIncidenteResponse();
        var incidente = incidenteRepository.findByIdAndDeleteAtIsNull(Integer.parseInt(incidenteId));

        if (incidente.isEmpty())
        {
            result.setError(true);
            result.setCode("404");
            result.setMessage(ResponseMessage.INCIDENTE_NOT_FOUND);

            logger.warn("El incidente no existe. Incidente Id:",incidenteId);
            return result;
        }

        var fechaActual = Instant.now();

        var incidenteToDelete = incidente.get();
        incidenteToDelete.setDeleteAt(fechaActual);

        incidenteRepository.save(incidenteToDelete);

        result.setError(false);
        result.setData(incidenteToDelete);
        result.setCode("200");
        return result;
    }

    public List<Incidente> buscarPorRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin)
    {
        return incidenteRepository.findAll((root, query, criteriaBuilder) -> {
            // Convertir LocalDate a Timestamp
            Timestamp timestampInicio = Timestamp.valueOf(fechaInicio.atStartOfDay());
            Timestamp timestampFin = Timestamp.valueOf(fechaFin.atTime(23, 59, 59));

            root.fetch("heridos", JoinType.LEFT);

            root.fetch("materiales", JoinType.LEFT);

            root.fetch("ubicacion", JoinType.LEFT);

            root.fetch("usuario", JoinType.LEFT);

            // Aplicar los predicados
            Predicate fechaInicioPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), timestampInicio);
            Predicate fechaFinPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), timestampFin);

            Predicate deleteAtPredicate = criteriaBuilder.isNull(root.get("deleteAt"));

            return criteriaBuilder.and(fechaInicioPredicate, fechaFinPredicate,deleteAtPredicate);
        });
    }

    public GetIncidenteResponse getIncidentes(String fechaCreacionInicial, String fechaCreacionFinal) {

        var result = new GetIncidenteResponse();

        var fechaInicio = LocalDate.parse(fechaCreacionInicial);
        var fechaFin = LocalDate.parse(fechaCreacionFinal);

        var resultado = buscarPorRangoDeFechas(fechaInicio,fechaFin);

        if (resultado.isEmpty()) {
            result.setError(true);
            result.setMessage("No hay incidentes respecto a los criterios elegidos");
            result.setCode("404");
            return  result;
        }

        result.setIncidentes(resultado);
        result.setCode("200");
        result.setMessage("OK");
        result.setError(false);

        return result;
    }
}
