# Build stage
# You can change this base image to anything else
# But make sure to use the correct version of Java
# Simply the artifact path
FROM adoptopenjdk/openjdk11:alpine-jre
ARG artifact=target/spring-boot-web.jar

WORKDIR /opt/app

COPY ${artifact} app.jar

# This should not be changed
ENTRYPOINT ["java","-jar","app.jar"]
