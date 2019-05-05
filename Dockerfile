FROM openjdk:8-jdk-alpine
COPY ./build/libs/magneto-0.0.1-SNAPSHOT.jar ./magneto.jar
ENTRYPOINT ["java","-jar","/magneto.jar"]
