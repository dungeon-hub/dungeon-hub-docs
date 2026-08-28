# Player Lookup and Safety

The lookup system checks Minecraft players and Discord users against supported safety services. It can also follow a
known Dungeon Hub link between a Discord account and Minecraft UUID, allowing both sides of an identity to be checked
together.

## Running a lookup

- Use [](lookup-player.topic) to check a Minecraft IGN.
- Use [](lookup-user.topic) to check a Discord account and its linked Minecraft account, when present.
- Right-click a Discord user and select the `Lookup` user command for the same Discord-user check.

An IGN lookup resolves the player's UUID and searches for a linked Dungeon Hub Discord account. A Discord-user lookup
does the reverse when the user has linked with [](link.topic). The result distinguishes between a clear lookup and one
where either identity is flagged.

## Checked services

The bot currently queries:

- Jerry / SkyblockZ
- Hypixel Safety

Each provider may check Minecraft UUIDs, Discord IDs, or both. Where supplied by the provider, the response can include
a reason, evidence, or staff information.

> A result of “not flagged” is not a guarantee that an account is safe. It only means the queried providers did not
> return an active flag for the supplied identities.
> {style="warning"}

## Partial results

Providers are queried independently. If one cannot be reached, the bot identifies the unavailable service and which
identity type could not be checked. Treat such a response as incomplete because an outage can cause a false negative.

## Player profiles

The [](player.topic) response includes the same flag summary alongside Hypixel profile information. Use the dedicated
lookup commands when safety is the main concern, and the player command when you also need in-game context.

## Appeals

[](lookup-appeal.topic) displays the current appeal destinations for the Jerry bot.
Appeal evidence should use uncropped screenshots so reviewers can evaluate its context.

## See also

- [](Player-Profiles.md)
- [](Verification.md)
- [](find-user.topic)
- [](ign.topic)
