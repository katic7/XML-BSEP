FROM anapsix/alpine-java
MAINTAINER Nemanja Dimsic <nemanjadimsic6@mgmail.com>
ADD target/agent-service-0.0.1-SNAPSHOT.jar agent-service.jar
ENTRYPOINT ["java", "-jar", "./agent-service.jar"]
EXPOSE 8090

