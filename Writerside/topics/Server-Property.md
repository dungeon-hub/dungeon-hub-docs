# Server Property

The server property is a way to store and manage server-specific settings that are used across multiple commands.
This includes settings like the moderation log channel, the strike log channel, the score management role, and more.

## Possible values

There are currently a set amount of possible server properties that can be set.
They can be managed using the sub-commands of [](config.topic), respective to the type of config property.

The following values can be set for the server property:

### String / Text {id="string"}

- `BAN_MESSAGE`: The message that is sent to a user when they are banned from the server. It can contain some
  placeholders:
    - `%\server%`: The name of the server
    - `%\form%`: The unban form, if present
- `UNBAN_FORM`: The link to the unban form that is sent to a user when they are banned from the server. If it is
  present, it will be attached to the message as a link button, so please make sure that it is in a proper link format.

### Number

`currently none`

### Boolean (true/false) {id="boolean"}

- `SCORE_ENABLED`: Currently unused.
- `COMPACT_LEADERBOARD`: If set to `true`, the leaderboards will be sent in a compact format, which means that all
  leaderboard entries will be shown in a single embed, in different fields. Otherwise, they will be sent in different
  embeds on the same message.
- `TOTAL_SCORE_EVENT`: If set to `true`, the total score leaderboard will include the event score.

### Channel

- `MODERATION_LOGS_CHANNEL`: The channel where moderation logs are sent to. This includes all messages about warnings
  and messages when a user is flagged.
- `SCORE_LOGS_CHANNEL`: The channel where special messages about the score system are sent to. This includes messages
  when a log was denied by a staff member and when score is managed (using [](manage-score.topic)).
- `STRIKES_LOGS_CHANNEL`: The channel where strike logs are sent to, meaning messages when a warning with [type
  `Strike`](Warning-Type.md) is added or edited.
- `LOG_APPROVING_CHANNEL`: The channel where carry logs are sent to for staff approval.
- `TRANSCRIPTS_CHANNEL`: The channel where transcripts are sent to. If the server uses Ticket Tool, please make sure
  that transcripts of all service tickets are sent to this channel. The bot will only listen to ticket transcripts in
  this channel, other channels will be ignored.
- `SERVICE_TEAM_RULES_CHANNEL`: Currently unused.
- `CNT_MESSAGES_CHANNEL`: The channel where crafts and transfer messages are sent to.
- `CNT_INFORMATION_CHANNEL`: The channel that contains information about the crafts and transfer system. This is used to
  link back to this channel in `/help`.

### Category

`currently none`

### Role

- `SCORE_MANAGEMENT_ROLE`: The score management role affect which users are allowed to manage the score of other users
  using the [](manage-score.topic) command. If a user does not have this role, they are not allowed to manage the score
  of other users, and a warning message will be shown to them when using that command. If this property isn't set, the
  user is required to have the `Administrator` permission to manage the score of other users. Additionally, this is
  required to discard the `/log` message of other users.
- `PURGE_IMMUNITY_ROLE`: If this is set, all users with this role will be immune from purges.
- `CNT_ROLE_REQUIREMENT_UNDER_THREE`: If set, this role is required to accept CNT requests with the value <3m.
- `CNT_ROLE_REQUIREMENT_THREE_TO_FIVE`: If set, this role is required to accept CNT requests with the value 3-5m.
- `CNT_ROLE_REQUIREMENT_FIVE_TO_TEN`: If set, this role is required to accept CNT requests with the value 5-10m.
- `CNT_ROLE_REQUIREMENT_TEN_TO_FIFTEEN`: If set, this role is required to accept CNT requests with the value 10-15m.
- `CNT_ROLE_REQUIREMENT_FIFTEEN_TO_TWENTY`: If set, this role is required to accept CNT requests with the value 15-20m.
- `CNT_ROLE_REQUIREMENT_TWENTY_TO_TWENTYFIVE`: If set, this role is required to accept CNT requests with the value 20-25m.
- `CNT_ROLE_REQUIREMENT_TWENTYFIVE_TO_FIFTY`: If set, this role is required to accept CNT requests with the value 25-50m.
- `CNT_ROLE_REQUIREMENT_FIFTY_TO_HUNDRED`: If set, this role is required to accept CNT requests with the value 50-100m.
- `CNT_ROLE_REQUIREMENT_HUNDRED_TO_TWOHUNDRED`: If set, this role is required to accept CNT requests with the value 100-200m.
- `CNT_ROLE_REQUIREMENT_TWOHUNDRED_TO_FOURHUNDRED`: If set, this role is required to accept CNT requests with the value 200-400m.
- `CNT_ROLE_REQUIREMENT_OVER_FOURHUNDRED`: If set, this role is required to accept CNT requests with the value >400m.
