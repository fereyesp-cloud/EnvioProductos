package envios.seguimiento.DTO;

import java.time.LocalDateTime;
import envios.seguimiento.model.EstadoEnvio;
import lombok.Data;

@Data
public class SeguimientoResponseDTO {

    private Long id;
    private String codigoSeguimiento;
    private EstadoEnvio estado;
    private String ubicacionActual;
    private String nombreDestinatario;
    private String direccionDestino;
    private String nombreProducto;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;
}