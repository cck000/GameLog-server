# Etapa 1: Construção (Build)
# Usamos uma imagem que já tem o Maven instalado para compilar o projeto
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Execução
# Usamos uma imagem leve apenas com o Java para rodar o app
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]