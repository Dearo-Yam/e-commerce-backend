#Buid using maven
FROM maven:3.8.6-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean -DskipTest
RUN mvn -f /home/app/pom.xml clean -DskipTest package

#package actual jar
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/*.jar /warehouse-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/warehouse-backend.jar"]
