FROM openjdk:17-alpine
ADD /target/*.jar gateway-service
EXPOSE 8080
ENTRYPOINT ["java","-jar","/gateway-service"]
