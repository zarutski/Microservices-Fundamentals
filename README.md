# Microservices Fundamentals

## Description

Practical part of the Microservices Fundamentals program

## Docker

Run the following commands to start docker containers:

`docker-compose build`

`docker compose --env-file ./config/.env up`

# Keycloak

To import keycloak realm:

- Open web browser and go to Keycloak login page. On the login page, enter `admin` for a username and `123456` for password.


- Within admin console click `Add realm` button. And import realm using `realm-export.json` file from `config/keycloak` folder.