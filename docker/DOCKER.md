

# Run Waltz

### Pre Requisites

* Docker
* Postgres DB instance

# Configuration

- Container will use default values to connect Waltz to DB and try to `update` Postgres database and `run` Waltz.
- You can change this with providing environment variables to container or as part of [docker-compose.yml](../docker-compose.yml)

### Default values
### Default command container will with startup is execution of both database `update` and `run` Waltz 

* `DB_HOST="postgres"}`
* `DB_PORT=${DB_PORT:-"5432"}`
* `DB_NAME="waltz"`
* `DB_USER="waltz"`
* `DB_PASSWORD="waltz"`
* `DB_SCHEME="waltz"`
* `WALTZ_FROM_EMAIL="help@finos.org"`
* `WALTZ_BASE_URL="http://127.0.0.1:8080/"`
* `CHANGELOG_FILE=_FILE="/opt/waltz/liquibase/db.changelog-master.xml"`

# Running

## Docker run

-Run waltz without updating database:

    $> docker run -it ghcr.io/[OWNER]/[REPO]:postgresql run


Run Waltz with updating new database:

    $> docker run -it ghcr.io/[OWNER]/[REPO]:postgresql \
      -e "DB_HOST=postgres" \
      -e "DB_NAME=waltz" \
      -e "DB_USER=waltz" \
      -e "DB_PASSWORD=waltz" \
      update run


## Docker-compose

To start you can use [docker-compose.yml](../docker-compose.yml) and run it with:

    $> docker-compose up -d 
