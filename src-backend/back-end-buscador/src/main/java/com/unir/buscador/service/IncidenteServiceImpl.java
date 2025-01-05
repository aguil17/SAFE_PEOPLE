package com.unir.buscador.service;

import com.unir.buscador.data.IIncidenteInformanteRepository;
import com.unir.buscador.data.IIncidenteRepository;
import com.unir.buscador.data.IInformanteRepository;
import com.unir.buscador.data.IMaterialRepository;
import com.unir.buscador.model.pojo.*;
import com.unir.buscador.model.request.CreateIncidenteRequest;
import com.unir.buscador.model.response.CreateIncidenteResponse;
import com.unir.buscador.model.response.GetIncidenteResponse;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public List<Incidente> buscarPorRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return incidenteRepository.findAll((root, query, criteriaBuilder) -> {
            // Convertir LocalDate a Timestamp
            Timestamp timestampInicio = Timestamp.valueOf(fechaInicio.atStartOfDay());
            Timestamp timestampFin = Timestamp.valueOf(fechaFin.atTime(23, 59, 59));

            // Aplicar los predicados
            Predicate fechaInicioPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("creation_date"), timestampInicio);
            Predicate fechaFinPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("creation_date"), timestampFin);
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

        Timestamp fechaActual = Timestamp.from(Instant.now());

        IncidentType incidentType = IncidentType.valueOf(request.getTipoIncidente());

        RoleType roleType = RoleType.valueOf(request.getRole());

        GenderType genderType = GenderType.valueOf(request.getGender());

        BigDecimal latitude = new BigDecimal(request.getLatitud());
        BigDecimal longitude = new BigDecimal(request.getLongitud());

        Incidente incidente = Incidente.builder()
                .incident_type(incidentType)
                .description_incident(request.getDescripcion())
                .date(LocalDate.parse(request.getFecha()))
                .time(LocalTime.parse(request.getHora()))
                .photo(request.getFoto())
                .creation_date(fechaActual)
                .deleteAt(null)
                .id_location(request.getIdLocation())
                .city_name(request.getNombreCiudad())
                .description_location(request.getDescripcion())
                .district_name(request.getNombreDistrito())
                .reference(request.getReferencia())
                .latitude(latitude)
                .longitude(longitude)
                .id_user(request.getId_user())
                .username(request.getUsername())
                .password_user(request.getPassword_user())
                .role(roleType)
                .id_person(request.getId_person())
                .person_name(request.getPerson_name())
                .person_last_name(request.getPerson_last_name())
                .dni(request.getDni())
                .email(request.getEmail())
                .cellphone(request.getCellphone())
                .password_person(request.getPassword_person())
                .birthdate(LocalDate.parse(request.getFecha()))
                .gender(genderType)
                .build();

        var incidenteSaved = incidenteRepository.save(incidente);

        if (request.getInformantes() != null) {
            for(var informante : request.getInformantes())
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

        if (request.getMateriales() != null) {
            for(var material : request.getMateriales())
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

        result.setCode("200");
        result.setMessage("OK");
        result.setError(false);

        return result;
    }
}