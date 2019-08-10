FROM openjdk:8-jdk-alpine
VOLUME /tmp
MAINTAINER Ritesh Ranjan <ritesh_ranjan@gmail.com>
ARG JAR_FILE=target/viride-app-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]