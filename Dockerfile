FROM amazoncorretto:17

COPY target/porfolio.jar porfolio.jar

ENTRYPOINT ["java", "-jar", "/porfolio.jar"]