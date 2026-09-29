# The image of the workshop: the official Amazon Corretto 21 runtime plus the jar.
FROM amazoncorretto:21

WORKDIR /app

# The jar is built before with: mvn clean package
COPY target/*.jar app.jar

# The port is configuration, not code: the container can be started with another one.
ENV PORT=9000

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]
