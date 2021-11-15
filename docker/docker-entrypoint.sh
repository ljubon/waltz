#!/bin/bash

check=( DB_HOST DB_PORT DB_NAME DB_USER DB_PASSWORD DB_SCHEME WALTZ_FROM_EMAIL WALTZ_BASE_URL CHANGELOG_FILE )
for x in "${check[@]}"
do
  if [ -z "$x" ]; then 
    echo "$x not provided"
    case $x in
      DB_HOST)
        export DB_HOST="postgres"
        ;;
      DB_PORT)
        export DB_PORT="5432"
        ;;
      DB_NAME | DB_USER | DB_PASSWORD | DB_SCHEME)
        export $x="waltz"
        ;;
      WALTZ_FROM_EMAIL)
        export WALTZ_FROM_EMAIL="help@finos.org"
        ;;
      WALTZ_BASE_URL)
        export WALTZ_BASE_URL="http://127.0.0.1:8080/"
        ;;
      CHANGELOG_FILE)
        export CHANGELOG_FILE="/opt/waltz/liquibase/db.changelog-master.xml"
        ;;
    esac
  fi
done


db_action () {
  while [[ $(pg_isready -U "${DB_USER}" -h "${DB_HOST}" -d "${DB_NAME}") == *"no response"* ]]
  do
    echo "Database is not ready yet."
    sleep 5s
  done

  pg_isready -U "${DB_USER}" -h "${DB_HOST}" -d "${DB_NAME}" && echo "Database is ready."

  # changeLogFile must be relative path
  DB_UPDATE=$(liquibase --changeLogFile=../../../${CHANGELOG_FILE} --hub-mode=off --username="${DB_USER}" --password="${DB_PASSWORD}" --url=jdbc:postgresql://"${DB_HOST}":"${DB_PORT}"/"${DB_NAME}" "$1")

  $DB_UPDATE

  # TODO - find better way to confirm executation of liquibase
  # sleep 9999
  # $DB_UPDATE 2> /dev/null || error_code=$?
  # if [[ $error_code -ne "0" ]]; then
  #   echo "Liquibase update failed"
  #   exit 1
  # fi
}

run_waltz () {
  envsubst < /home/waltz/.waltz/waltz-template.properties > /home/waltz/.waltz/waltz.properties
  catalina.sh run
}

while [[ "$#" -gt 0 ]]; do
    case $1 in
        update) echo ">>> Update DB" && db_action update ;;
        run) echo ">>> Run Waltz" && run_waltz ;;
    esac
    shift
done