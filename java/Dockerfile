ARG IRIS_IMAGE=intersystemsdc/iris-community:latest
FROM ${IRIS_IMAGE} AS irislibs

FROM eclipse-temurin:8-jdk AS builder

ARG APP_HOME=/tmp/app

COPY src $APP_HOME/src

COPY --from=irislibs /usr/irissys/dev/java/lib/1.8/intersystems-jdbc-3.10.5.jar $APP_HOME/jgw/
COPY --from=irislibs /usr/irissys/dev/java/lib/1.8/intersystems-utils-4.2.2.jar $APP_HOME/jgw/

WORKDIR $APP_HOME/jar/
ADD https://repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar .
ADD https://repo1.maven.org/maven2/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar .
ADD https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar .
ADD https://repo1.maven.org/maven2/org/json/json/20160810/json-20160810.jar .
ADD https://repo1.maven.org/maven2/javax/mail/mail/1.5.0-b01/mail-1.5.0-b01.jar .
ADD https://repo1.maven.org/maven2/org/telegram/telegrambots/5.3.0/telegrambots-5.3.0-jar-with-dependencies.jar .
ADD https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.8.11.2/sqlite-jdbc-3.8.11.2.jar .
ADD https://repo1.maven.org/maven2/javax/activation/activation/1.1.1/activation-1.1.1.jar .
ADD https://repo1.maven.org/maven2/com/opencsv/opencsv/5.3/opencsv-5.3.jar .

WORKDIR $APP_HOME/src

RUN javac -classpath $APP_HOME/jar/*:$APP_HOME/jgw/* Crypto/service/* && \
    jar -cvf $APP_HOME/jar/CryptoService.jar Crypto/service/*

RUN javac -classpath $APP_HOME/jar/*:$APP_HOME/jgw/* Crypto/operation/* && \
    jar -cvf $APP_HOME/jar/CryptoOperations.jar Crypto/operation/*

FROM intersystemsdc/jgw:latest AS jgwbase

# Java 8 TLS fingerprint is rejected by Cloudflare (CoinPaprika).
FROM eclipse-temurin:11-jre

ENV GWDIR=/jgw
ENV PORT=55555

COPY --from=jgwbase /jgw/ $GWDIR/
COPY --from=irislibs /usr/irissys/dev/java/lib/1.8/intersystems-jdbc-3.10.5.jar $GWDIR/
COPY --from=irislibs /usr/irissys/dev/java/lib/1.8/intersystems-utils-4.2.2.jar $GWDIR/
COPY --from=builder /tmp/app/jar/*.jar $GWDIR/
RUN rm -f $GWDIR/intersystems-jdbc-3.6.1.jar \
    && mkdir -p /jgw/db /jgw/output

CMD ["/bin/sh", "-c", "java -Xrs $JVMARGS -Djava.system.class.loader=com.intersystems.gateway.ClassLoader -classpath \"${GWDIR}/*\" com.intersystems.gateway.JavaGateway $PORT \"\" \"iris:IRIS:%Java Server\" 0.0.0.0 \"\" 2>&1"]
