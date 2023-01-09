FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY . .
RUN mvn package
EXPOSE 8089
ADD target/tpAchatProject-1.0.jar tpAchatProject-1.0.jar
ENTRYPOINT ["java","-jar","/tpAchatProject-1.0.jar"]