FROM openjdk:8
ADD ../mall-portal/target/mall-portal-1.0-SNAPSHOT.jar /mall-portal-1.0-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar","/mall-portal-1.0-SNAPSHOT.jar"]
