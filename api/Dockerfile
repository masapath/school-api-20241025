#イメージのベースを指定
FROM maven:3.8.5-eclipse-temurin-17-alpine AS builder

#作業ディレクトリの指定
WORKDIR /srv

COPY ./src ./src
COPY ./pom.xml .

RUN mvn package

FROM openjdk:17

COPY --from=builder /srv/target/api-0.0.1-SNAPSHOT.jar /target/api.jar

ENTRYPOINT ["java", "-jar", "/target/api.jar"]
