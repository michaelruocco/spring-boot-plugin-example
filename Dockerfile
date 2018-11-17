FROM openjdk:8-jre-alpine

USER 10001

COPY app/build/libs/app-*.jar /opt/app.jar

VOLUME opt/plugins
VOLUME opt/plugin-config

EXPOSE 8080

CMD ["/usr/bin/java", "-Djava.security.egd=file:/dev/./urandom", "-Dpf4j.pluginsDir=opt/plugins", "-jar", "/opt/app.jar"]