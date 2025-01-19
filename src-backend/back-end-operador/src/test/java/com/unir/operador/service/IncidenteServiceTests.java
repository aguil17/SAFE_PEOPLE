package com.unir.operador.service;

import com.unir.operador.data.IIncidenteRepository;
import com.unir.operador.model.pojo.Incidente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IncidenteServiceTests {

    @Mock
    private IIncidenteRepository incidenteRepository;

    @InjectMocks
    private IncidenteServiceImpl incidenteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
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
    }
}


