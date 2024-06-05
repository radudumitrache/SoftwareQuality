FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]