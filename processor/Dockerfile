FROM openjdk:17-alpine
ADD /target/*.jar processor-service
EXPOSE 8092
ENTRYPOINT ["java","-jar","/processor-service"]
