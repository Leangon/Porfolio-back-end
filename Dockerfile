FROM adoptopenjdk:17-jdk-hotspot
MAINTAINER LEA
COPY target/porfolio.jar porfolio.jar
ENTRYPOINT ["java","-jar","/porfolio.jar"]