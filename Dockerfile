# Estágio 1: Build da Aplicação
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
# Baixa as dependências primeiro para aproveitar o cache do Docker
RUN mvn dependency:go-offline
COPY src ./src
# Compila o projeto e pula os testes para agilizar o build (ajuste se desejar rodar testes)
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Imagem final leve)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Copia apenas o arquivo .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Define a porta que a aplicação irá expor
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]