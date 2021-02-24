FROM openjdk:14-alpine
RUN mkdir /app
ADD target/bugofficer-1.0-SNAPSHOT.jar /app/bugofficer.jar
VOLUME /app/data
WORKDIR /app
CMD java -jar bugofficer.jar
