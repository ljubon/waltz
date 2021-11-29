

# Run Waltz

### Pre Requisites

* Docker
* Postgres DB instance

# Configuration

- By default, container will use default values to connect Waltz to DB and try to `update` Postgres database and `run` Waltz.
- You can change this with providing environment variables to container or as part of [docker-compose.yml](../docker-compose.yml)

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
