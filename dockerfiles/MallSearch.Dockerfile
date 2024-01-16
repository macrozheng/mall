FROM openjdk:8
ADD mall-search-1.0-SNAPSHOT.jar /mall-search-1.0-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","/mall-search-1.0-SNAPSHOT.jar"]
