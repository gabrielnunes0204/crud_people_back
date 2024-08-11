# Estágio de construção
FROM ubuntu:latest AS build

# Atualiza os pacotes e instala o OpenJDK e Maven
RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
COPY . .

RUN apt-get install maven -y

# Compila o aplicativo
RUN mvn clean install

# Estágio de execução
FROM openjdk:8-jdk-slim

# Expõe a porta em que o aplicativo vai rodar
EXPOSE 8080

# Copia o JAR construído para o contêiner de execução
COPY --from=build /target/avaliacao-1.0.0.jar app.jar

# Define o ponto de entrada para o contêiner
ENTRYPOINT [ "java", "-jar", "app.jar"]