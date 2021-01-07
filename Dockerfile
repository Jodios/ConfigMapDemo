FROM openjdk:8-jdk-alpine
ENV _JAVA_OPTIONS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"
COPY target/configmapdemo-0.0.1-SNAPSHOT.jar  artifact.jar
EXPOSE 8081
CMD ["java","-jar","/artifact.jar"]