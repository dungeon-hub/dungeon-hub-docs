# Static Messages

Static messages are bot-managed embeds that can be created and refreshed to keep important information up to date in specific channels.

## Creating

Create a static message with [](static-message-create.topic). Choose the static message type and, optionally, a target text channel. \
The bot will post the message and return a link to it.

Available types are score, total, and reputation leaderboards, ticket panels, and price messages. Score leaderboards
use carry-type IDs, ticket panels use ticket-panel IDs, and price messages use carry-tier IDs. Total and reputation
leaderboards need no assigned objects. Use `/static-message find` to locate a message's ID and
`/static-message assign-object` to associate an object.

Static messages can have custom embed overrides. Configure an override through the
[Dungeon Hub Dashboard](https://dashboard.dungeon-hub.net/) or the API; it cannot be configured through a Discord bot
command. The supported fields are documented in [](Embed-Management.md).

## Updating

To refresh a static message, right-click it and select `Apps | Static Message Info`. Then select
`Update Static Message` in the ephemeral response. The bot re-renders the original message in place.

> Only registered static messages can be updated. Use `/static-message find` to check whether the bot still has a
> record for the message.
> {style="note"}

See also
- [](static-message.topic) for all management commands.
- [](leaderboard.topic) to view a temporary leaderboard response.
- [](send.topic) for other helper messages.
- [](Carry-and-Score-System.md) and [](Crafts-Transfers-and-Reputation.md) for the systems behind the leaderboard types.
