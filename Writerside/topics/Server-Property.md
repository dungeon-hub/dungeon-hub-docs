# Server Property ✏️

The server property is a way to store and manage server-specific settings that are used across multiple commands.
This includes settings like the moderation log channel, the strike log channel, the score management role, and more.

The settings can be changes using the sub-commands of [](config.topic). \
A list of all available server properties can be found below.

## String / Text

- `BAN_MESSAGE`: The message that is sent to a user when they are banned from the server. It can contain some
  placeholders:
    - `%\server%`: The name of the server
    - `%\form%`: The unban form, if present
- `UNBAN_FORM`: The link to the unban form that is sent to a user when they are banned from the server. If it is
  present, it will be attached to the message as a link button, so please make sure that it is in a proper link format.

## Number

`currently none`

## Boolean (true/false)

- `PROFILE_MODERATION_BAN`: If set to `true`, the bot will automatically ban users that have a flagged profile, meaning
  a suspicious username that might impersonate legitimate bots or users. Otherwise, only a log message is sent into the
  `MODERATION_LOGS_CHANNEL`.

## Channel

- `MODERATION_LOGS_CHANNEL`: The channel where moderation logs are sent to. This includes all messages about warnings
  and messages when a user is flagged.
- `SCORE_LOGS_CHANNEL`: The channel where special messages about the score system are sent to. This includes messages
  when a log was denied by a staff member and when score is managed (using [](manage-score.topic)).
- `STRIKES_LOGS_CHANNEL`: The channel where strike logs are sent to, meaning messages when a warning with [type
  `Strike`](Warning-Type.md) is added or edited.
- `LOG_APPROVING_CHANNEL`: `Coming soon`
- `TRANSCRIPTS_CHANNEL`: The channel where transcripts are sent to. If the server uses Ticket Tool, please make sure
  that transcripts of all service tickets are sent to this channel. The bot will only listen to ticket transcripts in
  this channel, other channels will be ignored.
- `TOTAL_SCORE_LEADERBOARD_CHANNEL`: The channel where the total score leaderboard is sent to. This leaderboard shows
  the total score of all users in the server.
- `SERVICE_TEAM_RULES_CHANNEL`: `Coming soon`
- `CNT_MESSAGES_CHANNEL`: `Coming soon`
- `CNT_INFORMATION_CHANNEL`: `Coming soon`

## Category

`currently none`

## Role

- `SCORE_MANAGEMENT_ROLE`: The score management role affect which users are allowed to manage the score of other users
  using the [](manage-score.topic) command. If a user does not have this role, they are not allowed to manage the score
  of other users, and a warning message will be shown to them when using that command. If this property isn't set, the
  user is required to have the `Administrator` permission to manage the score of other users. Additionally, this is
  required to discard the `/log` message of other users.