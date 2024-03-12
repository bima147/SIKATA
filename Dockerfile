FROM khipu/openjdk17-alpine

COPY target/SIKATA.jar /docker-build/SIKATA.jar

CMD ["java","-jar","/docker-build/SIKATA.jar"]