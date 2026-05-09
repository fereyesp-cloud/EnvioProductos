package envios.seguimiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import envios.seguimiento.DTO.SeguimientoRequestDTO;
import envios.seguimiento.DTO.SeguimientoResponseDTO;
import envios.seguimiento.exception.EnvioNotFoundException;
import envios.seguimiento.model.Seguimiento;
import envios.seguimiento.model.EstadoEnvio;
import envios.seguimiento.repository.SeguimientoRepository;
import envios.seguimiento.service.SeguimientoServiceImpl;

class SeguimientoApplicationTests {

    @Mock
    private SeguimientoRepository seguimientoRepository;

    @InjectMocks
    private SeguimientoServiceImpl seguimientoService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    // PRUEBA 1 
    @Test
    void createSeguimiento_deberia_retornar_response() {

        SeguimientoRequestDTO request = new SeguimientoRequestDTO();
        request.setCodigoSeguimiento("ENV-001");
        request.setNombreDestinatario("Juan Pérez");
        request.setDireccionDestino("Av. Siempre Viva 123");
        request.setNombreProducto("Alimento Royal Canin 15kg");
        request.setUbicacionActual("Santiago");

        Seguimiento guardado = new Seguimiento();
        guardado.setId(1L);
        guardado.setCodigoSeguimiento("ENV-001");
        guardado.setNombreDestinatario("Juan Pérez");
        guardado.setDireccionDestino("Av. Siempre Viva 123");
        guardado.setNombreProducto("Alimento Royal Canin 15kg");
        guardado.setUbicacionActual("Santiago");

        when(seguimientoRepository.save(any(Seguimiento.class))).thenReturn(guardado);

        SeguimientoResponseDTO response = seguimientoService.createSeguimiento(request);

        assertNotNull(response);
        assertEquals("ENV-001", response.getCodigoSeguimiento());
        assertEquals("Juan Pérez", response.getNombreDestinatario());
        verify(seguimientoRepository, times(1)).save(any(Seguimiento.class));
    }

    // PRUEBA 2 
    @Test
    void getAllSeguimientos_deberia_retornar_lista() {

        Seguimiento env1 = new Seguimiento();
        env1.setId(1L);
        env1.setCodigoSeguimiento("ENV-001");
        env1.setNombreProducto("Arena para gato 10kg");

        Seguimiento env2 = new Seguimiento();
        env2.setId(2L);
        env2.setCodigoSeguimiento("ENV-002");
        env2.setNombreProducto("Juguete Kong para perro");

        when(seguimientoRepository.findAll()).thenReturn(Arrays.asList(env1, env2));

        List<SeguimientoResponseDTO> resultado = seguimientoService.getAllSeguimientos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(seguimientoRepository, times(1)).findAll();
    }

    // PRUEBA 3 
    @Test
    void getSeguimientoBycodigo_deberia_retornar_envio_existente() {

        Seguimiento envio = new Seguimiento();
        envio.setId(1L);
        envio.setCodigoSeguimiento("ENV-001");
        envio.setNombreDestinatario("Juan Pérez");
        envio.setDireccionDestino("Av. Siempre Viva 123");
        envio.setNombreProducto("Arena para gato 10kg");
        envio.setUbicacionActual("Santiago");
        envio.setEstado(EstadoEnvio.PENDIENTE);

        when(seguimientoRepository.findByCodigoSeguimiento("ENV-001"))
                .thenReturn(Optional.of(envio));

        SeguimientoResponseDTO response = seguimientoService.getSeguimientoBycodigo("ENV-001");

        assertNotNull(response);
        assertEquals("ENV-001", response.getCodigoSeguimiento());
        assertEquals(EstadoEnvio.PENDIENTE, response.getEstado());
        assertEquals("Santiago", response.getUbicacionActual());
        verify(seguimientoRepository, times(1)).findByCodigoSeguimiento("ENV-001");
    }

    // PRUEBA 4 - 
    @Test
    void deleteSeguimiento_deberia_eliminar_envio_existente() {

        Seguimiento envio = new Seguimiento();
        envio.setId(1L);
        envio.setCodigoSeguimiento("ENV-001");
        envio.setNombreProducto("Juguete Kong para perro");
        envio.setUbicacionActual("Santiago");

        when(seguimientoRepository.findByCodigoSeguimiento("ENV-001"))
                .thenReturn(Optional.of(envio));

        doNothing().when(seguimientoRepository).delete(envio);

        assertDoesNotThrow(() -> seguimientoService.deleteSeguimiento("ENV-001"));

        verify(seguimientoRepository, times(1)).findByCodigoSeguimiento("ENV-001");
        verify(seguimientoRepository, times(1)).delete(envio);
    }
}