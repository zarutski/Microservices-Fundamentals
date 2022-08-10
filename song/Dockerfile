FROM openjdk:17-alpine
ADD /target/*.jar song-service
EXPOSE 8093
ENTRYPOINT ["java","-jar","/song-service"]
