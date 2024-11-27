FROM openjdk:17-jdk-slim

WORKDIR /app

COPY src /app/src/
COPY lib /app/libs/

RUN javac -cp "libs/*" $(find src -name "*.java") -d out

EXPOSE 8080

CMD ["java", "-cp", "libs/*:out", "App"]
