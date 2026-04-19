package envios.seguimiento.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SeguimientoRequestDTO {

    @NotBlank(message = "El código de seguimiento es obligatorio")
    @Size(min = 5, max = 20, message = "El código debe tener entre 5 y 20 caracteres")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "El código solo puede contener letras mayúsculas, números y guiones")
    private String codigoSeguimiento;

    @NotBlank(message = "La ubicación actual es obligatoria")
    @Size(max = 100, message = "La ubicación no puede superar los 100 caracteres")
    private String ubicacionActual;

    @NotBlank(message = "El nombre del destinatario es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombreDestinatario;

    @NotBlank(message = "La dirección de destino es obligatoria")
    @Size(max = 200, message = "La dirección no puede superar los 200 caracteres")
    private String direccionDestino;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 100, message = "El nombre del producto no puede superar los 100 caracteres")
    private String nombreProducto;
}