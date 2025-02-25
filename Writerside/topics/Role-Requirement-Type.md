# Role Requirement Type ✏️

Role requirements describe which requirement has to be met to receive a given role, depending on the server member's
data and their linked accounts.
This is checked every time the users roles are synced.

## Possible values

- Skyblock Level
- Catacombs Level
- Farming Level
- Mining Level
- Combat Level
- Fishing Level
- Skill Average
- Highest Skill
- Current Score
    - WIP, extra data is not implemented yet
- Alltime Score
    - WIP, extra data is not implemented yet
- Total Carries
    - WIP, not implemented yet
- Total Carries In Time Frame
    - WIP, not implemented yet
- Money Spent
- Money Spent In Time Frame
    - Extra data is [parsed](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.time/-duration/-companion/parse.html)
      as a [duration](https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.time/-duration/to-string.html)
- Hypixel Rank
    - The Hypixel rank is parsed to a number, with Player (no rank) being 0, VIP being 1, and so on. You can see the
      full list of possible
      ranks [here](https://github.com/dungeon-hub/hypixel-wrapper/blob/main/src/main/kotlin/net/dungeonhub/hypixel/entities/player/KnownRank.kt).
- Guild Membership
    - The extra data should be a guild name
    - If the user is in the guild, the number that will be compared to the role requirement is 1, otherwise 0
- Guild Rank
    - The extra data should be a guild name
    - The number that will be compared to the role requirement is the priority of the guild rank. This means that the
      lowest rank possible (usually the default rank) has a priority of 1, the highest rank (guild master) has the
      highest number. If the user is not in the guild of the extra data, the number returned is equal to 0.
- Magical Power
- Class Average
- Highest Crit Damage
    - WIP, not implemented yet
