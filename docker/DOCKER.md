

# Run Waltz as a container

### Pre Requisites

* Docker
* Postgres DB instance (optional, can run in Docker instead)

# Configuration

- The Waltz container will use default values to connect its DB.
- By default it will try to `update` its DB and then `run` Waltz.
- You can change this by providing environment variables to the container on the command line or as part of [docker-compose.yml](../docker-compose.yml)

## Default values and actions
The container will execute two commands: `update` and `run`. The first command will `update` the database instance by running `liquibase` against it. The second command `run` will execute `catalina.sh run` to `run` Waltz.

The default parameters are listed below:

* `DB_HOST="postgres"`
* `DB_PORT="5432"`
* `DB_NAME="waltz"`
* `DB_USER="waltz"`
* `DB_PASSWORD="waltz"`
* `DB_SCHEME="waltz"`
* `WALTZ_FROM_EMAIL="help@finos.org"`
* `WALTZ_BASE_URL="http://127.0.0.1:8080/"`
* `CHANGELOG_FILE=_FILE="/opt/waltz/liquibase/db.changelog-master.xml"`

# Running

## Docker Compose
To start Waltz with a Postgres instance in just one command, you can use [docker-compose.yml](../docker-compose.yml) and run it with:

    $> docker-compose up -d 

Once the container is up you can access the Waltz dashboard on [http://127.0.0.1:8080/](http://127.0.0.1:8080/)

## Docker run

Run waltz without updating the database:

    $> docker run -it ghcr.io/finos/waltz run

Update the database and run Waltz with new parameters:

    $> docker run -it ghcr.io/finos/waltz \
      -e "DB_HOST=existing_db" \
      -e "DB_PORT=5544" \
      -e "DB_NAME=demo" \
      -e "DB_USER=waltz" \
      -e "DB_PASSWORD=12345" update run