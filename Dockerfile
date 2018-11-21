FROM maven:3.5.0-jdk-8-alpine

LABEL maintainer "834033206@qq.com"

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY lion-web/target/lion-web-0.0.1.jar  /usr/src/myapp/lion-web-0.0.1.jar

WORKDIR /usr/src/myapp/

ENTRYPOINT java -jar lion-web-0.0.1.jar