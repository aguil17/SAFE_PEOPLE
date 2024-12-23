package com.unir.operador.service;
import com.unir.operador.data.IAccidenteRepository;
import com.unir.operador.model.pojo.Accidente;
import com.unir.operador.model.request.CreateAccidenteRequest;
import com.unir.operador.model.response.CreateAccidenteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccidenteServiceImpl implements AccidenteService {

    @Autowired
    private IAccidenteRepository accidenteRepository;
    public CreateAccidenteResponse crearAccidente(CreateAccidenteRequest request)
    {
        Accidente accidente = Accidente.builder().name(request.getDescripcion()).build();

        accidenteRepository.save(accidente);
        return new CreateAccidenteResponse();
    }
}
