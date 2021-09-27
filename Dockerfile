FROM tomcat:8-jdk8-openjdk

ENV PATH="/bin/liquibase:${PATH}" 
ENV LIQUIBASE_HOME="/bin/liquibase"

RUN mkdir -p /root/.waltz && \
  wget -q https://github.com/liquibase/liquibase-package-manager/releases/download/v0.1.2/lpm-0.1.2-linux.zip && \
  wget -q https://github.com/liquibase/liquibase/releases/download/v4.5.0/liquibase-4.5.0.zip && \
  unzip -qo lpm-0.1.2-linux.zip -d /bin && \
  unzip -qo liquibase-4.5.0.zip -d /bin/liquibase && \
  apt-get update && apt-get install -y postgresql-client gettext-base && \
  rm -rf /var/lib/apt/lists/* lpm-0.1.2-linux.zip liquibase-4.5.0.zip && \
  lpm update && lpm add -g postgresql

COPY docker/docker-entrypoint.sh /usr/local/tomcat/docker-entrypoint.sh
COPY waltz-data/src/main/ddl/liquibase/db.changelog-master.xml /usr/local/tomcat/db.changelog-master.xml
COPY ./waltz-web/target/waltz-web.war /usr/local/tomcat/webapps
COPY docker/waltz.properties /root/.waltz/waltz-template.properties
COPY waltz-web/src/main/resources/logback.example.xml /root/.waltz/waltz-logback.xml

EXPOSE 8080

ENTRYPOINT [ "./docker-entrypoint.sh" ]
CMD [ "update",  "run" ] 