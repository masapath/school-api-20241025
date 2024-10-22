#イメージのベースを指定
FROM maven:3.8.5-eclipse-temurin-17-alpine AS builder

#作業ディレクトリの指定
WORKDIR /srv

#COPY 〇〇 ×× で、イメージのファイルシステム上のパス(××)に〇〇を追加する
COPY ./src ./src
COPY ./pom.xml .

#イメージ作成時に、/bin/sh -c ○○の形でコマンドが実行される
RUN mvn package

#FROMは複数記述できる(それ以前の命令で作られた状態が、クリアになることに注意)
FROM openjdk:17

#--from=別名 で、構築したイメージを参照できる
COPY --from=builder /srv/target/api-0.0.1-SNAPSHOT.jar /target/api.jar

#コンテナ起動時に実行するコマンドを指定
ENTRYPOINT ["java", "-jar", "/target/api.jar"]
