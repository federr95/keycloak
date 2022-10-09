#
# Build stage
#
FROM maven:3.8.6 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /home/app/target/web_app_test-0.0.1-SNAPSHOT.jar /usr/local/lib/web_app_test-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/web_app_test-0.0.1-SNAPSHOT.jar"]

