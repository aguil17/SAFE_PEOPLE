package com.unir.buscador.service;

import com.unir.buscador.data.IIncidenteRepository;
import com.unir.buscador.model.pojo.GenderType;
import com.unir.buscador.model.pojo.IncidentType;
import com.unir.buscador.model.pojo.Incidente;
import com.unir.buscador.model.pojo.RoleType;
import com.unir.buscador.model.request.CreateIncidenteRequest;
import com.unir.buscador.model.response.CreateIncidenteResponse;
import com.unir.buscador.model.response.GetIncidenteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class IncidenteServiceImpl implements IIncidenteService {

    @Autowired
    private IIncidenteRepository incidenteRepository;

    public GetIncidenteResponse getIncidentes() {

        var result = new GetIncidenteResponse();

        var resultado = incidenteRepository.findAll();

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

        incidenteRepository.save(incidente);

        result.setCode("200");
        result.setMessage("OK");
        result.setError(false);

        return result;
    }
}
