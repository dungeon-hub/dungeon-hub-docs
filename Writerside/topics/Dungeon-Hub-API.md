# Dungeon Hub API ✏️

## What is the Dungeon Hub API

The Dungeon Hub API hosts all data that is used in the [](Dungeon-Hub-Bot.md). \
The [](Dungeon-Hub-Bot.md) itself only serves as a client to interact with the API - it does not have any database
access and only persists data through the API.
A documentation with all endpoints and their usage can be found at [](https://api.dungeon-hub.net/).

## Authentication

Most endpoints of the Dungeon Hub API require authentication. \
The API uses bearer tokens for authentication, meaning an Authorization header containing the JWT token must be attached
to the request. \
The header should look like: `Authorization: Bearer <token>`. \
A token can be obtained from the authentication server, which is hosted separately to the API
at [](https://auth.dungeon-hub.net/).

### Logging in as a user

`Coming soon`

### Logging in as a client

> This requires a client to be registered manually. If you're interested in this, please
> contact [the administration](mailto:admin@dungeon-hub.net).
> {style=warning}

To log in as a client, you need to send a `POST` request to
`https://auth.dungeon-hub.net/realms/dungeon-hub/protocol/openid-connect/token`. \
The request body should be a multipart form containing the following fields:

- `client_id`: `dh-server`
- `grant_type`: `client_credentials`

As the Authorization header, you should use basic authentication with the client's credentials (client id as username,
client secret as password).
