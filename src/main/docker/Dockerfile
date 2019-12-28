FROM openjdk:11-slim

VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} mqtt.jar

RUN addgroup --gid 1025 iot
RUN adduser --system --disabled-password --disabled-login --uid 1025 --gid 1025 iot
RUN mkdir -p /opt/metao && chown -R iot:iot /opt/metao
RUN mkdir -p /var/log/metao/iot && chown -R iot:iot /var/log/metao/iot
USER iot
RUN touch /var/log/metao/iot/spring.log

COPY build/libs/${JAR_FILE} /opt/iot/${JAR_FILE}
RUN ln -s /opt/metao/${JAR_FILE} /opt/metao/iot.jar

ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["java", "-jar", "/opt/metap/iot.jar"]