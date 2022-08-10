FROM openjdk:17-alpine
ADD /target/*.jar resource-service
EXPOSE 8091
ENTRYPOINT ["java","-jar","/resource-service"]
