package com.envio.envioproductos.controlle;

import org.springframework.web.bind.annotation.*;

import com.envio.envioproductos.model.productosDatos;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;



@RestController
public class productosDatosControlle {

    public List<productosDatos> productos = new ArrayList<>();
    private int nexId = 9;

    public productosDatosControlle(){
        //**
        // 8 envios precargados
        productos.add(new productosDatos(1, "Croquetas Premium", "Juan Pérez",
                "Av. Siempre Viva 123, Santiago", "ENTREGADO",
                "Santiago Centro", "TRK-001"));
        
        productos.add(new productosDatos(2, "Collar Antipulgas", "María López",
                "Calle Los Pinos 45, Valparaíso", "EN_CAMINO",
                "Terminal Valparaíso", "TRK-002"));

        productos.add(new productosDatos(3, "Cama para Gato", "Carlos Ruiz",
                "Pasaje El Roble 78, Concepción", "PENDIENTE",
                "Bodega Central Santiago", "TRK-003"));

        productos.add(new productosDatos(4, "Vitaminas Perro", "Ana Torres",
                "Los Alerces 200, Temuco", "EN_CAMINO",
                "Chillán", "TRK-004"));

        productos.add(new productosDatos(5, "Juguete Interactivo", "Pedro Soto",
                "Calle Larga 99, La Serena", "PENDIENTE",
                "Bodega Central Santiago", "TRK-005"));

        productos.add(new productosDatos(6, "Arena para Gato", "Lucía Mora",
                "Av. del Mar 310, Iquique", "EN_CAMINO",
                "Antofagasta", "TRK-006"));

        productos.add(new productosDatos(7, "Correa Extensible", "Roberto Díaz",
                "Calle Central 55, Rancagua", "ENTREGADO",
                "Rancagua", "TRK-007"));

        productos.add(new productosDatos(8, "Shampoo Mascotas", "Sofía Vega",
                "Los Olivos 88, Talca", "PENDIENTE",
                "Bodega Central Santiago", "TRK-008"));

    }

       //*
        // GET: listar todos los envios */
        @GetMapping("/envios")
        public List<productosDatos> getALLenvios(){
            return productos;
        }

        //*
        // GET: buscar envio por ID */
          @GetMapping("/envios/{id}")
        public ResponseEntity<productosDatos> getEnvioById(@PathVariable int id) {
            for (productosDatos producto : productos) {
                if (producto.getId() == id) {
                    return ResponseEntity.ok(producto);
                }
        }
        return ResponseEntity.notFound().build();
    }
     //*
        // GET: buscar codigo de seguimiento*/
        @GetMapping("/productos/codigo/{code}")
        public ResponseEntity<productosDatos> getByTracking(@PathVariable String codigo) {
        for (productosDatos producto : productos) {
            if (producto.getCodigoSeguimiento().equalsIgnoreCase(codigo)) {
                return ResponseEntity.ok(producto);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // GET: filtrar por envios por estados*/
    @GetMapping("/envios/estado/{estado}")
    public ResponseEntity<List<productosDatos>> getByEstado(@PathVariable String estado) {
        if (!estado.equalsIgnoreCase("PENDIENTE") &&
            !estado.equalsIgnoreCase("EN_CAMINO") &&
            !estado.equalsIgnoreCase("ENTREGADO")) {
            return ResponseEntity.badRequest().build();
        }
        List<productosDatos> resultado = new ArrayList<>();
        for (productosDatos producto : productos) {
            if (producto.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(producto);
            }
        }
        return ResponseEntity.ok(resultado);
    }

    //GET: registrar nuevo envío por parámetros
        @GetMapping("/envios/nuevo")
    public ResponseEntity<String> addEnvio(
            @RequestParam String nombreProducto,
            @RequestParam String nombreDueno,
            @RequestParam String destino,
            @RequestParam String trackingCode) {

        if (nombreProducto.isBlank() || nombreDueno.isBlank() ||
            destino.isBlank() || trackingCode.isBlank()) {
            return ResponseEntity.badRequest().body("Error: ningún campo puede estar vacío.");
        }
        for (productosDatos producto : productos) {
            if (producto.getCodigoSeguimiento().equalsIgnoreCase(trackingCode)) {
                return ResponseEntity.badRequest()
                        .body("Error: ya existe un envío con el código " + trackingCode);
            }
        }
        productos.add(new productosDatos(nexId++, nombreProducto, nombreDueno,
                destino, "PENDIENTE", "Bodega Central Santiago", trackingCode));
        return ResponseEntity.ok("Envío registrado. ID asignado: " + (nexId - 1));
    }
        
    
    //GET: registrar nuevo envío por parámetros
   @GetMapping("/envios/actualizar/{id}")
    public ResponseEntity<String> updateEnvio(
            @PathVariable int id,
            @RequestParam String estado,
            @RequestParam String ubicacion) {

        if (!estado.equalsIgnoreCase("PENDIENTE") &&
            !estado.equalsIgnoreCase("EN_CAMINO") &&
            !estado.equalsIgnoreCase("ENTREGADO")) {
            return ResponseEntity.badRequest()
                    .body("Error: estado inválido. Use PENDIENTE, EN_CAMINO o ENTREGADO.");
        }
        if (ubicacion.isBlank()) {
            return ResponseEntity.badRequest().body("Error: la ubicación no puede estar vacía.");
        }
        for (productosDatos producto : productos) {
            if (producto.getId() == id) {
                producto.setStatus(estado.toUpperCase());
                producto.setCurrentLocation(ubicacion);
                return ResponseEntity.ok("Envío ID " + id + " actualizado correctamente.");
            }
        }
        return ResponseEntity.notFound().build();
    } 
}
