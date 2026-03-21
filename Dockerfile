FROM maven:3.9.6-eclipse-temurin-21 AS build
LABEL authors="Omistaja"

WORKDIR /app

COPY pom.xml .

COPY . /app

RUN mvn package

CMD ["java","-jar", "target/Software-Engineering-Project-2-2026-Assignment-1-1.0-SNAPSHOT.jar"]