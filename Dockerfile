FROM amazoncorretto:17-alpine-jdk
MAINTAINER LEA
COPY target/porfolio.jar porfolio.jar
ENTRYPOINT ["java","-jar","/porfolio.jar"]