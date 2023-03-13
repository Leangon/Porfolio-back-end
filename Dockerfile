FROM amazoncorretto:17

COPY target/porfolio.jar porfolioapp.jar

ENTRYPOINT ["java", "jar", "/porfolioapp.jar"]