FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y bash bash-completion vim && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/*.jar app.jar
COPY src/main/resources/application.properties ./application.properties

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=classpath:/application.properties,file:/app/application.properties"]

