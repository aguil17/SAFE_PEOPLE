package com.unir.operador.service;
import com.unir.operador.data.IIncidenteRepository;
import com.unir.operador.data.IUbicacionRepository;
import com.unir.operador.model.request.CreateIncidenteRequest;
import com.unir.operador.model.request.UpdateIncidenteRequest;
import com.unir.operador.model.response.CreateIncidenteResponse;
import com.unir.operador.model.response.DeleteIncidenteResponse;
import com.unir.operador.model.response.UpdateIncidenteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.unir.operador.model.pojo.*;

@Service
public class IncidenteServiceImpl implements IncidenteService {

    @Autowired
    private IIncidenteRepository incidenteRepository;

    @Autowired
    private IUbicacionRepository ubicacionRepository;
    @Autowired
    private ResourceLoader resourceLoader;

    public CreateIncidenteResponse crearIncidente(CreateIncidenteRequest request)
    {
        var result = new CreateIncidenteResponse();

        var ubicacion = ubicacionRepository.getById(request.getIdLocation());

        if (ubicacion == null)
        {
            result.setError(true);
            result.setMessage("La ubicación indicada no existe");
            result.setCode("404");
            return result;
        }

        Incidente accidente = Incidente.builder()
                .tipoIncidente(request.getTipoIncidente())
                .descripcion(request.getDescripcion())
                .fecha(request.getFecha())
                .hora(request.getHora())
                .foto(request.getFoto())
                .idLocation(request.getIdLocation()).build();

        incidenteRepository.save(accidente);
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
        var incidente = incidenteRepository.getById(Integer.parseInt(incidenteId));

        if (incidente == null)
        {
            result.setError(true);
            result.setCode("404");
            result.setMessage("El incidente no existe");
            return result;
        }

        ubicacionRepository.deleteById(Integer.parseInt(incidenteId));

        result.setError(false);
        result.setData(incidente);
        result.setCode("200");
        return result;
    }
}
