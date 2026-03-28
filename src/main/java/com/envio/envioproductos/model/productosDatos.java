package com.envio.envioproductos.model;
/**
 * Clase que representa los datos en el sistema.
 * Contiene los atributos básicos de información de un producto.
 */
public class productosDatos {
    
     /** Identificador único del procuto */
    private int id;
       /** Nombre del producto*/
    private String nombreProducto;
    /** Nombre del dueño*/
    private String nombreDueño;
    /** Dirrecion del envio*/
    private String dirreccion;
     /** Estado del envio*/
    private String estado;
    /** Ubicacion del producto*/
    private String ubicacion;
    /** Codigo del seguimiento*/
    private String codigoSeguimiento;

    /**
     * Constructor completo para crear una película con todos sus atributos.
     *
     * @param id       Nombre del producto
     * @param nombreProducto  NNombre del producto
     * @param nombreDueño      Nombre del dueño
     * @param dirreccion Dirrecion del envio
     * @param estado   Estado del envio*
     * @param ubicacion  Ubicacion del producto
     * @param codigoSeguimiento Codigo del seguimiento
     */

    public productosDatos (int id, String nombreProducto, String nombreDueño, String dirreccion, String estado, String ubicacion, String codigoSeguimiento  ){

        this.id = id;
        this.nombreProducto = nombreProducto;
        this.nombreDueño = nombreDueño;
        this.dirreccion = dirreccion;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.codigoSeguimiento = codigoSeguimiento;
    }

    public int getId() {
        return id;
    }
    public String getNombreProducto() {
         return nombreProducto; 
    }
    public String getNombreDueño() {
         return nombreDueño; 
    }
    public String getDestination() {
         return dirreccion; 
    }
    public String getEstado() {
         return estado; 
    }
    public String getUbicacion() {
         return ubicacion; 
    }
    public String getCodigoSeguimiento() {
         return codigoSeguimiento; 
    }

    // Setters
    public void setStatus(String estado) { 
        this.estado = estado; 
    }
    public void setCurrentLocation(String ubicacion) {
         this.ubicacion = ubicacion; 
    }
}
