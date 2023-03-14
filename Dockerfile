FROM amazoncorretto:17

MAINTAINER LEANDRO

COPY target/porfolio.jar porfolio.jar

ENTRYPOINT ["java", "-jar", "/porfolio.jar"]