FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/app.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
