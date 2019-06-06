FROM anapsix/alpine-java
MAINTAINER Nemanja Dimsic <nemanjadimsic6@gmail.com>
ADD target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "./auth-service.jar"]
EXPOSE 8085

