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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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

    private IncidentesFacade incidentesFacade;

    public CreateIncidenteResponse crearIncidente(CreateIncidenteRequest request)
    {
        var result = new CreateIncidenteResponse();

        Incidente incidente = Incidente.builder()
                .incident_type(Integer.parseInt(request.getTipoIncidente()))
                .descripcion(request.getDescripcion())
                .date(LocalDate.parse(request.getFecha()))
                .time(LocalTime.parse(request.getHora()))
                .photo(request.getFoto())
                .id_location(Integer.parseInt(request.getIdUbicacion())).build();

        var requestUbicacion = request.getUbicacion();

        Ubicacion ubicacion = Ubicacion.builder().city_name(requestUbicacion.getNombreCiudad())
                .descripcion(requestUbicacion.getDescripcion())
                .district_name(requestUbicacion.getNombreDistrito())
                .reference(requestUbicacion.getReferencia())
                .latitude(requestUbicacion.getLatitud())
                .longitude(requestUbicacion.getLongitud())
                .build();

        ubicacionRepository.save(ubicacion);

        incidenteRepository.save(incidente);

        incidentesFacade.RegistrarIncidente(request);

        for (var material : request.getMateriales())
        {
            var materialEntity = Material.builder()
                    .material_type(material.getTipoMaterial())
                    .description(material.getDescripcion())
                    .quantity(Integer.parseInt(material.getCantidad()))
                    .material_condition(material.getCondicionMaterial()).build();
            materialRepository.save(materialEntity);
        }

        for (var herido : request.getHeridos())
        {

        }

        result.setError(false);
        result.setData(incidente);
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
        var incidente = incidenteRepository.findById(Integer.parseInt(incidenteId));

        if (incidente.isEmpty())
        {
            result.setError(true);
            result.setCode("404");
            result.setMessage("El incidente no existe");
            return result;
        }

        ubicacionRepository.deleteById(Integer.parseInt(incidenteId));

        result.setError(false);
        result.setData(incidente.get());
        result.setCode("200");
        return result;
    }
}
