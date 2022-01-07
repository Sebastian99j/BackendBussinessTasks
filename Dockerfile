FROM openjdk:8u201-jdk-alpine3.9
ADD target/BackendBussinessTasks-0.0.1-SNAPSHOT.jar .
EXPOSE 8083
CMD java -jar BackendBussinessTasks-0.0.1-SNAPSHOT.jar --envname=prod