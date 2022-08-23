FROM openjdk:17-alpine
ADD /target/*.jar storage-service
EXPOSE 8094
ENTRYPOINT ["java","-jar","/storage-service"]
