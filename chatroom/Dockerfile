FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY chat-application/target/chat-application-0.0.1-SNAPSHOT.jar /app/chat-application.jar
EXPOSE 8080
CMD ["java", "-jar", "chat-application.jar"]

