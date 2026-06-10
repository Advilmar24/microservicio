# Usar una imagen oficial de OpenJDK runtime
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo
WORKDIR /app

# Copiar archivos de Maven y el proyecto
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descargar dependencias
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto, sin tests
RUN ./mvnw package -DskipTests

# Exponer el puerto (ajusta según el microservicio)
EXPOSE 8081

# Ejecutar el JAR específico del microservicio
ENTRYPOINT ["java", "-jar", "target/catalogo-0.0.1-SNAPSHOT.jar"]