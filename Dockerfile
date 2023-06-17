FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/customer-transaction-0.0.1-SNAPSHOT.jar customer-transaction.jar
ENTRYPOINT ["java", "-jar", "customer-transaction.jar"]