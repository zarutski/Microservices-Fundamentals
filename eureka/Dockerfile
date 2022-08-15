FROM openjdk:17-alpine
ADD /target/*.jar eureka-service
EXPOSE 8761
ENTRYPOINT ["java","-jar","/eureka-service"]
