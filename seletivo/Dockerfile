# Usando JDK 21
FROM eclipse-temurin:21-jdk-jammy

# Diretório de trabalho
WORKDIR /app

# Copia arquivos do Maven e baixa dependências (cache)
COPY pom.xml .
RUN mkdir -p src && echo "" > src/placeholder
RUN ./mvnw dependency:go-offline || true

# Copia o restante do código
COPY . .

# Build da aplicação
RUN ./mvnw clean package -DskipTests

# Expõe porta da aplicação
EXPOSE 8080

# Comando para rodar o JAR
CMD ["java", "-jar", "target/seletivo-1.0.0.jar"]
