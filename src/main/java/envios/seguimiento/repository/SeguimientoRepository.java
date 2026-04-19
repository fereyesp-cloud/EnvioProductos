package envios.seguimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import envios.seguimiento.model.Seguimiento;
import java.util.Optional;

public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> {

    // Buscar por código de seguimiento 
    Optional<Seguimiento> findByCodigoSeguimiento(String codigoSeguimiento);
}
