FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY target/EMERCOM-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT java -jar /app/EMERCOM-0.0.1-SNAPSHOT.jar