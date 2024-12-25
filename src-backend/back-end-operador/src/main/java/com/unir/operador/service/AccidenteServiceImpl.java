package com.unir.operador.service;
import com.unir.operador.data.IIncidenteRepository;
import com.unir.operador.model.request.CreateIncidenteRequest;
import com.unir.operador.model.response.CreateIncidenteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unir.operador.model.pojo.*;

@Service
public class AccidenteServiceImpl implements AccidenteService {

    @Autowired
    private IIncidenteRepository accidenteRepository;
    public CreateIncidenteResponse crearAccidente(CreateIncidenteRequest request)
    {
        Incidente accidente = Incidente.builder().name(request.getDescripcion()).build();

        accidenteRepository.save(accidente);
        return new CreateIncidenteResponse();
    }
}
