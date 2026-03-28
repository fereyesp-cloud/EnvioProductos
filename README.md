# version
1.0.0

# Tecnologías
Java 17
Spring Boot 3.2.0
Maven

# Endpoints disponibles

GET http://localhost:8080/envios

GET http://localhost:8080/envios/{id}

GET http://localhost:8080/productos/codigo/{code}

GET http://localhost:8080//envios/estado/{estado}

GET http://localhost:8080/envios/nuevo

GET http://localhost:8080/envios/actualizar/{id}

Cómo ejecutar
# Clonar el repositorio
git clone <url-del-repositorio>

# Entrar al directorio
cd ENVIOPRODUCTOS

# Ejecutar con Maven
./mvnw spring-boot:run
El servicio estará disponible en: http://localhost:8080