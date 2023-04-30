FROM openjdk:17-oracle
MAINTAINER LEA
COPY target/porfolio.jar porfolio.jar
ENTRYPOINT ["java","-jar","/porfolio.jar"]