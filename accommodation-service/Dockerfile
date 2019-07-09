FROM anapsix/alpine-java
MAINTAINER Nemanja Dimsic <nemanjadimsic6@gmail.com>
VOLUME /tmp
ADD target/accommodation-service-0.0.1-SNAPSHOT.jar accommodation-service.jar
ENTRYPOINT ["java", "-jar", "./accommodation-service.jar"]
EXPOSE 8082

