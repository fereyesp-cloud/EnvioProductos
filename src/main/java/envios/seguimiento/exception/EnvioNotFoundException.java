package envios.seguimiento.exception;

public class EnvioNotFoundException extends RuntimeException {
    public EnvioNotFoundException(String mensaje) {
        super(mensaje);
    }
}