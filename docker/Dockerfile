FROM openjdk:8-jdk-alpine

RUN apk update && apk add tzdata
RUN cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime
RUN echo "America/Sao_Paulo" > /etc/timezone
RUN date
RUN apk del tzdata

ENV SPRING_PROFILE=notset
ENV CONFIG_SERVICE=https://btsconfigservice.ciandt.com

ADD ./skeleton.jar /opt/api.jar

ENTRYPOINT exec java -jar -Dspring.profiles.active="${SPRING_PROFILE}" -Dspring.cloud.config.uri="${CONFIG_SERVICE}" /opt/api.jar