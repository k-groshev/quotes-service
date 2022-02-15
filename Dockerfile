FROM adoptopenjdk/openjdk14:alpine-jre as builder
WORKDIR application
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar
RUN unzip application.jar

FROM adoptopenjdk/openjdk14:alpine-jre
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

VOLUME /tmp
WORKDIR application

COPY --from=builder application/BOOT-INF/lib ./app/lib
COPY --from=builder application/META-INF ./app/META-INF
COPY --from=builder application/BOOT-INF/classes ./app

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:9080/actuator/health || exit 1

ENTRYPOINT ["java","-cp","app:app/lib/*", "-Xmx50m", "-Djava.security.egd=file:/dev/./urandom" ,"net.groshev.quote.App"]

EXPOSE 9080