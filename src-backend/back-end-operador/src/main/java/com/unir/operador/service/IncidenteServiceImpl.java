package com.unir.operador.service;
import com.unir.operador.data.IIncidenteRepository;
import com.unir.operador.data.IMaterialRepository;
import com.unir.operador.data.IUbicacionRepository;
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

    private IncidentesFacade incidentesFacade;

    public CreateIncidenteResponse crearIncidente(CreateIncidenteRequest request)
    {
        var result = new CreateIncidenteResponse();

        var ubicacion = ubicacionRepository.findById(Integer.parseInt(request.getIdUbicacion()));

        if (ubicacion.isEmpty())
        {
            result.setError(true);
            result.setMessage("La ubicación indicada no existe");
            result.setCode("404");
            return result;
        }

        Incidente accidente = Incidente.builder()
                .incident_type(Integer.parseInt(request.getTipoIncidente()))
                .descripcion(request.getDescripcion())
                .date(LocalDate.parse(request.getFecha()))
                .time(LocalTime.parse(request.getHora()))
                .photo(request.getFoto())
                .id_location(Integer.parseInt(request.getIdUbicacion())).build();

        incidenteRepository.save(accidente);

        incidentesFacade.RegistrarIncidente(request);

        result.setError(false);
        result.setData(accidente);
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
