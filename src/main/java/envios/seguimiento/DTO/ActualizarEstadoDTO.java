package envios.seguimiento.DTO;

import envios.seguimiento.model.EstadoEnvio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarEstadoDTO {

    @NotNull(message = "El estado es obligatorio")
    private EstadoEnvio estado;

    @NotBlank(message = "La ubicación actual es obligatoria")
    private String ubicacionActual;
}