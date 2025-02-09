package com.unir.buscador.service;

import com.unir.buscador.data.*;
import com.unir.buscador.model.pojo.*;
import com.unir.buscador.model.request.CreateIncidenteRequest;
import com.unir.buscador.model.response.CreateIncidenteResponse;
import com.unir.buscador.model.response.GetIncidenteResponse;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.JoinType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class IncidenteServiceImpl implements IIncidenteService {

    @Autowired
    private IIncidenteRepository incidenteRepository;

    @Autowired
    private IInformanteRepository informanteRepository;

    @Autowired
    private IIncidenteInformanteRepository incidenteInformanteRepository;

    @Autowired
    private IMaterialRepository materialRepository;

    @Autowired
    private IHeridoRepository heridoRepository;

    public List<Incidente> buscarPorRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return incidenteRepository.findAll((root, query, criteriaBuilder) -> {
            // Convertir LocalDate a Timestamp
            Timestamp timestampInicio = Timestamp.valueOf(fechaInicio.atStartOfDay());
            Timestamp timestampFin = Timestamp.valueOf(fechaFin.atTime(23, 59, 59));


            root.fetch("heridos", JoinType.LEFT);

            root.fetch("materiales", JoinType.LEFT);

            root.fetch("incidenteInformantes", JoinType.LEFT);

            // Aplicar los predicados
            Predicate fechaInicioPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), timestampInicio);
            Predicate fechaFinPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), timestampFin);
            return criteriaBuilder.and(fechaInicioPredicate, fechaFinPredicate);
        });
    }

    public GetIncidenteResponse getIncidentes(String fechaCreacionInicial,String fechaCreacionFinal) {

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

    public CreateIncidenteResponse createIncidente(CreateIncidenteRequest request)
    {
        var result = new CreateIncidenteResponse();

        var fechaActual = Instant.now();

        IncidentType incidentType = IncidentType.valueOf(request.getTipoIncidente());

        RoleType roleType = null;
        if (request.getRole() != null)
        {
            roleType = RoleType.valueOf(request.getRole());
        }

        GenderType genderType = null;
        if (request.getGender() != null)
        {
            genderType = GenderType.valueOf(request.getGender());
        }

        BigDecimal latitude = new BigDecimal(request.getLatitud());
        BigDecimal longitude = new BigDecimal(request.getLongitud());

        Incidente incidente = Incidente.builder()
                .incidentType(incidentType)
                .descriptionIncident(request.getDescripcion())
                .date(LocalDate.parse(request.getFecha()))
                .time(LocalTime.parse(request.getHora()))
                .photo(request.getFoto())
                .creationDate(fechaActual)
                .deleteAt(null)
                .idLocation(request.getIdLocation())
                .cityName(request.getNombreCiudad())
                .descriptionLocation(request.getDescripcion())
                .districtName(request.getNombreDistrito())
                .reference(request.getReferencia())
                .latitude(latitude)
                .longitude(longitude)
                .idUser(request.getId_user())
                .username(request.getUsername())
                .passwordUser(request.getPassword_user())
                .role(roleType)
                .idPerson(request.getId_person())
                .personName(request.getPerson_name())
                .personLastName(request.getPerson_last_name())
                .dni(request.getDni())
                .email(request.getEmail())
                .cellphone(request.getCellphone())
                .passwordPerson(request.getPassword_person())
                .birthdate(LocalDate.parse(request.getFecha()))
                .gender(genderType)
                .build();

        var incidenteSaved = incidenteRepository.save(incidente);

        if (request.getInformantes() != null) {
            for (var informante : request.getInformantes())
            {
                var incidenteInformante = IncidenteInformante.builder()
                        .name(informante.getNombre())
                        .lastName(informante.getApellidos())
                        .cellphone(informante.getCelular())
                        .email(informante.getCorreoElectronico())
                        .incidente(incidenteSaved)
                        .assignmentDate(fechaActual)
                        .creationDate(fechaActual)
                        .build();

                incidenteInformanteRepository.save(incidenteInformante);
            }
        }

        if (request.getMateriales() != null) {
            for(var material : request.getMateriales())
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
                GenderType genero = GenderType.valueOf(herido.getGenero());

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

        result.setCode("200");
        result.setMessage("OK");
        result.setData(incidenteSaved);
        result.setError(false);

        return result;
    }
}