FROM openjdk:11-jdk-slim

WORKDIR /app

COPY src /app/src/
COPY lib /app/libs/

RUN javac -cp "libs/*" $(find src -name "*.java") -d out

CMD ["java", "-cp", "libs/*:out", "App"]
