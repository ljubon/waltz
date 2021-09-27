#!/bin/bash

db_action () {
  while [[ $(pg_isready -U "${DB_USER}" -h "${DB_HOST}" -d "${DB_NAME}") == *"no response"* ]]
  do
    echo "Database is not ready yet."
    sleep 5s
  done

  pg_isready -U "${DB_USER}" -h "${DB_HOST}" -d "${DB_NAME}" && echo "Database is ready."

  # changeLogFile must be relative path
  DB_UPDATE=$(liquibase --changeLogFile=../../../${CHANGELOG_FILE} --hub-mode=off --username="${DB_USER}" --password="${DB_PASSWORD}" --url=jdbc:postgresql://"${DB_HOST}":"${DB_PORT}"/"${DB_NAME}" "$1")

  if $DB_UPDATE; then
    echo "Success"
  else
    echo "Liquibase update failed"
    exit 1
  fi
  
}

run_waltz () {
  envsubst < /root/.waltz/waltz-template.properties > /root/.waltz/waltz.properties
  catalina.sh run
}

DEFAULT_DB_COMMAND="update"
DB_COMMAND="${1:-$DEFAULT_DB_COMMAND}"
DEFAULT_WALTZ_COMMAND="run"
WALTZ_COMMAND="${1:-$DEFAULT_WALTZ_COMMAND}"

if [[ $DB_COMMAND == "$DEFAULT_DB_COMMAND" && -z $2 ]]; then
  echo ">>> Update DB"
  db_action "$1"
elif  [[ $WALTZ_COMMAND == "$DEFAULT_WALTZ_COMMAND" && -z $2 ]]; then
  echo ">>> Run Waltz"
  run_waltz
else
  echo ">>> Update DB and Run Waltz"
  db_action update
  run_waltz
fi
