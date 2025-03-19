FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY target/inditex-0.0.1-SNAPSHOT.jar inditex-service.jar

ENTRYPOINT ["java","-jar","/inditex-service.jar"]