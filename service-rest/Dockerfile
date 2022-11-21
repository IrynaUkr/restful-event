FROM adoptopenjdk/openjdk11:ubi

COPY ["target/service-rest-1.0-SNAPSHOT.jar", "event.jar" ]
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "event.jar"]
