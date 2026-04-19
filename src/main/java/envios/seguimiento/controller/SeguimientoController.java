package envios.seguimiento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import envios.seguimiento.DTO.ActualizarEstadoDTO;
import envios.seguimiento.DTO.SeguimientoRequestDTO;
import envios.seguimiento.DTO.SeguimientoResponseDTO;
import envios.seguimiento.service.SeguimientoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/seguimientos")
@CrossOrigin(origins = "*")
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;

    // GET - Listar todos los envíos
    @GetMapping
    public ResponseEntity<List<SeguimientoResponseDTO>> getAllSeguimientos() {
        return ResponseEntity.ok(seguimientoService.getAllSeguimientos());
    }

    // GET - Consultar ubicación por código de seguimiento
    @GetMapping("/{codigo}")
    public ResponseEntity<SeguimientoResponseDTO> getSeguimiento(@PathVariable String codigo) {
        return ResponseEntity.ok(seguimientoService.getSeguimientoBycodigo(codigo));
    }

    // POST - Registrar nuevo envío
    @PostMapping
    public ResponseEntity<SeguimientoResponseDTO> createSeguimiento(
            @Valid @RequestBody SeguimientoRequestDTO requestDTO) {
        SeguimientoResponseDTO response = seguimientoService.createSeguimiento(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT - Actualizar estado y ubicación
    @PutMapping("/{codigo}")
    public ResponseEntity<SeguimientoResponseDTO> updateEstado(
            @PathVariable String codigo,
            @Valid @RequestBody ActualizarEstadoDTO actualizarEstadoDTO) {
        SeguimientoResponseDTO response = seguimientoService.updateEstado(codigo, actualizarEstadoDTO);
        return ResponseEntity.ok(response);
    }

    // DELETE - Eliminar envío por código
    @DeleteMapping("/{codigo}")
    public ResponseEntity<String> deleteSeguimiento(@PathVariable String codigo) {
        seguimientoService.deleteSeguimiento(codigo);
        return ResponseEntity.ok("Envío eliminado correctamente");
    }
}