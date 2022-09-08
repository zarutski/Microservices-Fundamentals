FROM openjdk:17-alpine
ADD /target/*.jar gateway-service
EXPOSE 9990
ENTRYPOINT ["java","-jar","/gateway-service"]
