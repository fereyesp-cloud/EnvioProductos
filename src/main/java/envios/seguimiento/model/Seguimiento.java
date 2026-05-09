package envios.seguimiento.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seguimiento")
public class Seguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_seguimiento")
    @SequenceGenerator(name = "seq_seguimiento", sequenceName = "SEQ_SEGUIMIENTO", allocationSize = 1)
    private Long id;

    @Column(name = "codigo_seguimiento", nullable = false, unique = true)
    private String codigoSeguimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEnvio estado;

    @Column(name = "ubicacion_actual", nullable = false)
    private String ubicacionActual;

    @Column(name = "nombre_destinatario", nullable = false)
    private String nombreDestinatario;

    @Column(name = "direccion_destino", nullable = false)
    private String direccionDestino;

    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // Asignar automáticamente la fecha de registro y estado PENDIENTE
    @PrePersist
    public void antesDeGuardar() {
        this.fechaRegistro = LocalDateTime.now();
        this.estado = EstadoEnvio.PENDIENTE;
    }

    // Actualizar la fecha de modificación
    @PreUpdate
    public void antesDeActualizar() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}