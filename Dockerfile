FROM maven:3.8-openjdk-18-slim AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package
#-DskipTests


FROM maven:3.8-openjdk-18-slim

WORKDIR /app

COPY --from=builder /app/target/calculator-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
