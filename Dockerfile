FROM eclipse-temurin:17-jdk AS buildstage

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY wallet /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk

COPY --from=buildstage /app/target/seguimiento-0.0.1-SNAPSHOT.jar /app/seguimiento.jar

COPY wallet /app/wallet

ENV TNS_ADMIN=/app/wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/seguimiento.jar"]