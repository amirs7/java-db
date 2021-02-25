FROM maven:3.6-openjdk-15-slim as builder
#RUN mkdir /build
#ADD ./pom.xml /build
#WORKDIR /build
#RUN mvn dependency:go-offline
#ADD ./src /build/src
#RUN mvn dependency:go-offline
#RUN ls /root/.m2/repository
ADD target/bugofficer-1.0-SNAPSHOT.jar /build/target/

FROM openjdk:14-alpine
RUN mkdir /app
COPY --from=builder /build/target/bugofficer-1.0-SNAPSHOT.jar /app/bugofficer.jar
VOLUME /app/data
WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=docker
CMD java -jar bugofficer.jar