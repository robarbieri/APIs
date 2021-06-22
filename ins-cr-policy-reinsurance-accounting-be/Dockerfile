FROM openjdk:8-jdk-alpine
VOLUME /app
COPY target/app.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar","./app.jar"]