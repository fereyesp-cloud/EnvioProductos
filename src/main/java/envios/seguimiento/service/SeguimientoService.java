package envios.seguimiento.service;

import java.util.List;

import envios.seguimiento.DTO.ActualizarEstadoDTO;
import envios.seguimiento.DTO.SeguimientoRequestDTO;
import envios.seguimiento.DTO.SeguimientoResponseDTO;

public interface SeguimientoService {

    // Registrar un nuevo envío
    SeguimientoResponseDTO createSeguimiento(SeguimientoRequestDTO requestDTO);

    // Actualizar estado y ubicación de un envío
    SeguimientoResponseDTO updateEstado(String codigoSeguimiento, ActualizarEstadoDTO actualizarEstadoDTO);

    // Consultar ubicación por código de seguimiento
    SeguimientoResponseDTO getSeguimientoBycodigo(String codigoSeguimiento);

    // Listar todos los envíos
    List<SeguimientoResponseDTO> getAllSeguimientos();

    //Eliminar envios
    void deleteSeguimiento(String codigoSeguimiento);
}