package com.unir.operador.service;

import com.unir.operador.data.IIncidenteRepository;
import com.unir.operador.data.IUbicacionRepository;
import com.unir.operador.facade.IncidentesFacade;
import com.unir.operador.model.pojo.Incidente;
import com.unir.operador.model.pojo.Ubicacion;
import com.unir.operador.model.request.CreateIncidenteRequest;
import com.unir.operador.model.request.CreateLocationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IncidenteServiceTests {

    @Mock
    private IIncidenteRepository incidenteRepository;

    @Mock
    private IUbicacionRepository ubicacionRepository;

    @Mock
    private IncidentesFacade incidentesFacade;

    @InjectMocks
    private IncidenteServiceImpl incidenteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    public void testCrearIncidente_success()
    {
        //arrange
        CreateIncidenteRequest request = new CreateIncidenteRequest();
        request.setDescripcion("Fuego en av. principal");
        request.setTipoIncidente("fire");
        request.setFecha("2020-10-12");
        request.setHora("10:30");

        var ubicacion = new CreateLocationRequest();
        ubicacion.setDescripcion("Fuego en av. principal");
        ubicacion.setNombreCiudad("Lima");
        ubicacion.setNombreDistrito("Lima");
        ubicacion.setReferencia("Esquina Avenida Pardo y Aliaga");
        ubicacion.setLatitud("-12.046374");
        ubicacion.setLongitud("-77.042793");

        request.setUbicacion(ubicacion);

        var ubicacionResponse = new Ubicacion();
        ubicacionResponse.setDescription("Fuego en av. principal");
        ubicacionResponse.setCityName("Lima");
        ubicacionResponse.setDistrictName("Lima");
        ubicacionResponse.setReference("Esquina Avenida Pardo y Aliaga");
        ubicacionResponse.setLatitude("-12.046374");
        ubicacionResponse.setLongitude("-77.042793");

        when(ubicacionRepository.save(any())).thenReturn(ubicacionResponse);

        var fecha = LocalDate.parse("2020-10-12");
        var hora = LocalTime.parse("10:30");

        var incidente = new Incidente();
        incidente.setId(1);
        incidente.setDescription("Fuego en av. principal");
        incidente.setDate(fecha);
        incidente.setTime(hora);

        when(incidenteRepository.save(any())).thenReturn(incidente);

        //act
        var result = incidenteService.crearIncidente(request);

        //assert
        verify(ubicacionRepository,times(1)).save(any());
        verify(incidenteRepository,times(1)).save(any());
        assertNotNull(result);
        assertFalse(result.isError());
        assertEquals("201",result.getCode());
        assertEquals(incidente.getId(),result.getData().getId());
        assertEquals(incidente.getDescription(),result.getData().getDescription());
        assertEquals(incidente.getDate(),result.getData().getDate());
        assertEquals(incidente.getTime(),result.getData().getTime());
    }

    @Test
    public void testEliminarIncidente_NoExisteIncidente() {
        //arrange
        when(incidenteRepository.findByIdAndDeleteAtIsNull(1)).thenReturn(Optional.empty());

        //act
        var response = incidenteService.eliminarIncidente("1");

        //assert
        verify(incidenteRepository,times(1)).findByIdAndDeleteAtIsNull(1);
        assertTrue(response.isError());
        assertEquals("404",response.getCode());
        assertEquals("El incidente no existe",response.getMessage());
    }

    @Test
    public void testEliminarIncidente_ExisteIncidente() {
        //arrange
        Incidente incidente = new Incidente();
        incidente.setId(1);

        when(incidenteRepository.findByIdAndDeleteAtIsNull(1)).thenReturn(Optional.of(incidente));

        //act
        var response = incidenteService.eliminarIncidente("1");

        //assert
        verify(incidenteRepository,times(1)).findByIdAndDeleteAtIsNull(1);
        verify(incidenteRepository,times(1)).save(any(Incidente.class));
        assertFalse(response.isError());
        assertEquals("200",response.getCode());
        assertEquals(incidente.getId(),response.getData().getId());
    }
}


