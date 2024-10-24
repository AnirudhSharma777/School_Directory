FROM openjdk:23-jdk

WORKDIR /app

# COPY build/libs/postgresSpring-0.0.1-SNAPSHOT.jar app.jar
COPY build/libs/*.jar app.jar

EXPOSE 8080

CMD [ "java", "-jar" , "app.jar" ]
