FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY --from=build target/tpAchatProject-1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]