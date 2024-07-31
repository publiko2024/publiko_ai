FROM openjdk:21

RUN mkdir -p deploy
WORKDIR /deploy

COPY ./build/libs/ai-0.0.1-SNAPSHOT.jar ai-server.jar

ENTRYPOINT ["java","-jar","/deploy/ai-server.jar"]