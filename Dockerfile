FROM tomcat:8-jre8-temurin

ENV PATH="/usr/local/bin/liquibase:${PATH}" 

RUN useradd -ms /bin/bash waltz && \
  mkdir -p /home/waltz/.waltz && \
  chown -R waltz /usr/local/tomcat /home/waltz/.waltz && \
  curl -sLO https://github.com/liquibase/liquibase-package-manager/releases/download/v0.1.2/lpm-0.1.2-linux.zip && \
  curl -sLO https://github.com/liquibase/liquibase/releases/download/v4.5.0/liquibase-4.5.0.zip && \
  apt-get update && apt-get install -y unzip postgresql-client gettext-base && \
  unzip -qo lpm-0.1.2-linux.zip -d /usr/local/bin && \
  unzip -qo liquibase-4.5.0.zip -d /usr/local/bin/liquibase && \
  rm -rf /var/lib/apt/lists/* lpm-0.1.2-linux.zip liquibase-4.5.0.zip && \
  lpm update && lpm add -g postgresql

COPY docker/docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
COPY ./waltz-data/src/main/ddl/liquibase/*.xml /opt/waltz/liquibase/
COPY ./waltz-web/target/waltz-web.war /usr/local/tomcat/webapps
COPY docker/waltz.properties /home/waltz/.waltz/waltz-template.properties
COPY waltz-web/src/main/resources/logback.example.xml /home/waltz/.waltz/waltz-logback.xml

EXPOSE 8080

USER waltz

ENTRYPOINT [ "/usr/local/bin/docker-entrypoint.sh" ]
CMD [ "update",  "run" ] 