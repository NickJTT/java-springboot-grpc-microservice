# Step 1: Use Maven to build the JAR
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src 
RUN mvn clean package -DskipTests

# Step 2: Run the JAR with a smaller image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

RUN apk add --no-cache bash
COPY wait-for-it.sh ./wait-for-it.sh
RUN chmod +x ./wait-for-it.sh

ENTRYPOINT ["/bin/bash", "./wait-for-it.sh", "postgres:5432", "--timeout=30", "--", "java", "-jar", "app.jar"]
