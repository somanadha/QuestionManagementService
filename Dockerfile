FROM openjdk:24-slim-bullseye
ADD target/question-management-service.jar question-management-service.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "question-management-service.jar"]