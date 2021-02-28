FROM openjdk:8
ADD target/task-manager-spring.jar task-manager-spring.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","task-manager-spring.jar"]