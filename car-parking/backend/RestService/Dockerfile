
FROM maven:3.6.3-openjdk-8 as build
WORKDIR /app
COPY ./pom.xml ./
RUN mvn -e -B dependency:go-offline
COPY ./src ./src/
RUN mvn clean package

FROM openjdk:8-jdk-alpine
COPY target/RestService-0.0.1-SNAPSHOT.jar /RestService.jar
CMD ["java", "-jar", "/RestService.jar"]
