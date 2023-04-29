FROM amazoncorretto:17

MAINTAINER leandro

COPY target/porfolio.jar porfolio.jar

ENTRYPOINT ["java", "-jar", "/porfolio.jar"]