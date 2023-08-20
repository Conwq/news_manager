FROM maven:3.9.3-amazoncorretto-20-debian-bookworm as maven
WORKDIR /app
COPY . .
RUN mvn clean package

FROM tomcat:9.0.78-jdk21-openjdk-slim-bookworm
COPY --from=maven /app/target/java-course-project-spring.war /usr/local/tomcat/webapps
EXPOSE 8080