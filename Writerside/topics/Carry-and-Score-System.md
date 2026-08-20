# Carry and Score System

The carry and score system records services completed through Dungeon Hub tickets and turns them into score for service
team members. Carry configuration also supplies prices, display names, thumbnails, and leaderboard groupings used by
other bot features.

## Carry structure

Carry configuration has three levels:

1. A [carry type](Carry-Type.md) groups a service, such as dungeons, and can define a logging channel or enable event score.
2. A [carry tier](Carry-Tier.md) belongs to a carry type and groups related difficulties. Ticket panels can be linked to a tier.
3. A [carry difficulty](Carry-Difficulty.md) belongs to a tier and defines values such as price, score, bulk pricing, and display information.

The [](calc-price.topic) command uses this configuration to calculate the price for an amount of carries. When a bulk
amount and price are configured and the requested amount reaches that threshold, the bulk price is used.

## Logging a carry

The normal logging workflow is tied to the [](Ticket-System.md):

1. A ticket panel is associated with a carry tier.
2. A carrier runs [](log.topic) in the ticket and selects a difficulty and amount.
3. The carrier confirms the pending log. Only one confirmation-stage log can exist for the ticket at a time.
4. When the ticket transcript is created, the transcript URL is attached to the queued log as evidence.
5. The bot either records the carry directly or sends it for staff approval.

If `LOG_APPROVING_CHANNEL` is configured, a combined log requires approval when it contains at least 11 carries or is
worth at least 34 score. Staff can accept or deny it and can adjust the amount of a single queued entry. Smaller logs,
or all logs when no approval channel is configured, are recorded directly.

> The thresholds are currently defined by the bot and cannot be changed through server properties.
> {style="note"}

## Score

Every recorded carry updates the carrier's score for its carry type. The available [score types](Score-Type.md) are:

- `Default`: the current score used by normal rankings and purge thresholds.
- `Alltime`: the lifetime score, which is not changed by bulk score resets.
- `Event`: awarded only while the carry type has its event enabled.

Use [](score.topic) to inspect a member's score and [](leaderboard-score.topic) to compare members. Leaderboards can be
filtered by carry type and score type. Administrators can publish continuously refreshed versions through
[](Static-Messages.md).

## Administrative adjustments

Authorized staff can use [](manage-score.topic) to add, remove, or reset score. These operations are separate from
normal carry logging and are written to `SCORE_LOGS_CHANNEL` when that property is configured. Access is controlled by
`SCORE_MANAGEMENT_ROLE`; without that property, the bot requires Administrator.

## Purging inactive members

The [](purge.topic) workflow finds members whose default score is below a selected threshold and queues configured
role removals. Members holding `PURGE_IMMUNITY_ROLE` are excluded. Always preview a purge before adding its actions,
and review its progress before starting it, because an active purge cannot be cleared through the command.

## See also

- [](Ticket-Panel-Configuration.md)
- [](Server-Property.md)
- [](leaderboard.topic)
- [](Static-Messages.md)
