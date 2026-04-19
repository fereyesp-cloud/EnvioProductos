package envios.seguimiento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import envios.seguimiento.DTO.ActualizarEstadoDTO;
import envios.seguimiento.DTO.SeguimientoRequestDTO;
import envios.seguimiento.DTO.SeguimientoResponseDTO;
import envios.seguimiento.model.Seguimiento;
import envios.seguimiento.repository.SeguimientoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SeguimientoServiceImpl implements SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Override
    public SeguimientoResponseDTO createSeguimiento(SeguimientoRequestDTO requestDTO) {
        log.info("Registrando nuevo envío con código: {}", requestDTO.getCodigoSeguimiento());

        Seguimiento seguimiento = new Seguimiento();
        seguimiento.setCodigoSeguimiento(requestDTO.getCodigoSeguimiento());
        seguimiento.setUbicacionActual(requestDTO.getUbicacionActual());
        seguimiento.setNombreDestinatario(requestDTO.getNombreDestinatario());
        seguimiento.setDireccionDestino(requestDTO.getDireccionDestino());
        seguimiento.setNombreProducto(requestDTO.getNombreProducto());

        Seguimiento guardado = seguimientoRepository.save(seguimiento);
        log.info("Envío registrado exitosamente con ID: {}", guardado.getId());
        return convertirAResponse(guardado);
    }

    @Override
    public SeguimientoResponseDTO updateEstado(String codigoSeguimiento, ActualizarEstadoDTO actualizarEstadoDTO) {
        log.info("Actualizando estado del envío: {} → nuevo estado: {}", codigoSeguimiento, actualizarEstadoDTO.getEstado());

        Seguimiento seguimiento = seguimientoRepository
                .findByCodigoSeguimiento(codigoSeguimiento)
                .orElseThrow(() -> {
                    log.error("Envío no encontrado con código: {}", codigoSeguimiento);
                    return new RuntimeException("Envío no encontrado: " + codigoSeguimiento);
                });

        seguimiento.setEstado(actualizarEstadoDTO.getEstado());
        seguimiento.setUbicacionActual(actualizarEstadoDTO.getUbicacionActual());

        Seguimiento actualizado = seguimientoRepository.save(seguimiento);
        log.info("Estado actualizado correctamente para el envío: {}", codigoSeguimiento);
        return convertirAResponse(actualizado);
    }

    @Override
    public SeguimientoResponseDTO getSeguimientoBycodigo(String codigoSeguimiento) {
        log.info("Consultando envío con código: {}", codigoSeguimiento);

        Seguimiento seguimiento = seguimientoRepository
                .findByCodigoSeguimiento(codigoSeguimiento)
                .orElseThrow(() -> {
                    log.error("Envío no encontrado con código: {}", codigoSeguimiento);
                    return new RuntimeException("Envío no encontrado: " + codigoSeguimiento);
                });

        log.debug("Envío encontrado: ID={}, estado={}, ubicación={}", 
            seguimiento.getId(), seguimiento.getEstado(), seguimiento.getUbicacionActual());
        return convertirAResponse(seguimiento);
    }

    @Override
    public List<SeguimientoResponseDTO> getAllSeguimientos() {
        log.info("Consultando todos los envíos");

        List<Seguimiento> seguimientos = seguimientoRepository.findAll();
        log.debug("Total de envíos encontrados: {}", seguimientos.size());

        return seguimientos.stream()
                .map(this::convertirAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSeguimiento(String codigoSeguimiento) {
        log.warn("Eliminando envío con código: {}", codigoSeguimiento);

        Seguimiento seguimiento = seguimientoRepository
                .findByCodigoSeguimiento(codigoSeguimiento)
                .orElseThrow(() -> {
                    log.error("No se puede eliminar. Envío no encontrado con código: {}", codigoSeguimiento);
                    return new RuntimeException("Envío no encontrado: " + codigoSeguimiento);
                });

        seguimientoRepository.delete(seguimiento);
        log.warn("Envío eliminado correctamente: {}", codigoSeguimiento);
    }

    private SeguimientoResponseDTO convertirAResponse(Seguimiento seguimiento) {
        SeguimientoResponseDTO response = new SeguimientoResponseDTO();
        response.setId(seguimiento.getId());
        response.setCodigoSeguimiento(seguimiento.getCodigoSeguimiento());
        response.setEstado(seguimiento.getEstado());
        response.setUbicacionActual(seguimiento.getUbicacionActual());
        response.setNombreDestinatario(seguimiento.getNombreDestinatario());
        response.setDireccionDestino(seguimiento.getDireccionDestino());
        response.setNombreProducto(seguimiento.getNombreProducto());
        response.setFechaRegistro(seguimiento.getFechaRegistro());
        response.setFechaActualizacion(seguimiento.getFechaActualizacion());
        return response;
    }
}