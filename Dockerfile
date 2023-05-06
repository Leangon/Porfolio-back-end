FROM amazoncorretto:17
MAINTAINER LEA
EXPOSE 8080
COPY target/porfolio.jar porfolioApp.jar
ENTRYPOINT ["java","-jar","/porfolioApp.jar"]