FROM openjdk:8
ADD mall-admin-1.0-SNAPSHOT.jar /mall-admin-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/mall-admin-1.0-SNAPSHOT.jar"]
