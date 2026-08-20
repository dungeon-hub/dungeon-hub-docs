# Player Profiles

The bot can display rich player profiles based on Hypixel data using the [dungeon-hub/hypixel-wrapper](https://github.com/dungeon-hub/hypixel-wrapper/) library.

## Showing a profile

- Use [](player.topic) with an IGN to fetch and display a player’s profile, including a SkyCrypt button.
- In carry tickets, the bot may automatically post a player profile for quick context.

## Safety checks

Use [](lookup.topic) to check whether a player (IGN) or Discord user is flagged across supported services. The profile and lookup complement each other: the profile shows in-game context, while the lookup focuses on risk signals. See [](Player-Lookup-and-Safety.md) for the complete safety workflow and the limitations of lookup results.

## See also

- [](lookup.topic)
- [](Player-Lookup-and-Safety.md)
- [](player.topic)
