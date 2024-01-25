FROM openjdk:8
ADD mall-portal-1.0-SNAPSHOT.jar /mall-portal-1.0-SNAPSHOT.jar
ADD arex-agent.jar /usr/local/tomcat/
ADD arex-agent-bootstrap.jar /usr/local/tomcat/
ENV JAVA_TOOL_OPTIONS="-javaagent:/usr/local/tomcat/arex-agent.jar -Darex.service.name=Arex-Mall -Darex.storage.service.host=10.144.62.53:8093 -Darex.enable.debug=true"
EXPOSE 8085
ENTRYPOINT ["java", "-jar","/mall-portal-1.0-SNAPSHOT.jar"]