# Ticket Placeholders

Placeholders allow you to dynamically insert user, ticket, and system data into channel names, messages, and transcripts. This page provides a complete reference of all available placeholders for the ticket system.

## Where Placeholders Work

Placeholders can be used in:
- **Channel naming templates**: `openChannelName`, `claimedChannelName`, `closedChannelName`
- **Ticket welcome messages**: `ticketMessage` content and embeds
- **Transcript DM messages**: `userTranscriptDm`

## User Placeholders

Placeholders for the user who **created** the ticket.

| Placeholder          | Description                             | Example Output          |
|----------------------|-----------------------------------------|-------------------------|
| `{user.id}`          | Discord user ID                         | `123456789012345678`    |
| `{user.mention}`     | Discord mention format                  | `<@123456789012345678>` |
| `{user.name}`        | Discord username                        | `techsupport42`         |
| `{user.globalName}`  | Discord global display name             | `Tech Support`          |
| `{user.displayName}` | Server-specific nickname or global name | `Staff - Tech`          |

**Example usage:**

```text
Welcome, {user.mention}! Your ticket ID is {ticket.id}.
```

## Minecraft & Game Placeholders

Placeholders for the ticket creator's linked Minecraft account and game statistics. These require the user to have a linked account via [](link.topic).

| Placeholder              | Description                  | Example Output |
|--------------------------|------------------------------|----------------|
| `{user.minecraft.name}`  | Minecraft IGN (In-Game Name) | `Technoblade`  |
| `{user.skyblock.level}`  | Hypixel SkyBlock level       | `287`          |
| `{user.catacombs.level}` | Catacombs (dungeon) level    | `42`           |

> If the user is not linked, these placeholders will resolve to empty strings or fallback values.
> {style="note"}

**Example usage:**

```text
Player: {user.minecraft.name} (Catacombs {user.catacombs.level})
```

## Ticket Placeholders

Placeholders for ticket-specific information.

| Placeholder      | Description                                                       | Example Output       |
|------------------|-------------------------------------------------------------------|----------------------|
| `{ticket.id}`    | Unique ticket ID                                                  | `123`                |
| `{ticket.count}` | Sequential ticket number (number of ticket created in that panel) | `42`                 |
| `{ticket.name}`  | Ticket channel name                                               | `support-taubsie-42` |
| `{panel.name}`   | Ticket panel name                                                 | `Support`            |

**Example usage:**

```text
ticket-{ticket.count}
```

## Form Response Placeholders

Placeholders for user responses to form questions. The number corresponds to the question's position in the `formQuestions` array (1-indexed).

| Placeholder       | Description                       | Example Output                 |
|-------------------|-----------------------------------|--------------------------------|
| `{ticket.form.1}` | Response to the 1st form question | `I can't log in to my account` |
| `{ticket.form.2}` | Response to the 2nd form question | `High`                         |
| `{ticket.form.3}` | Response to the 3rd form question | `5`                            |
| `{ticket.form.4}` | Response to the 4th form question | `Additional notes here`        |
| `{ticket.form.5}` | Response to the 5th form question | `Yes`                          |

> Form questions are limited to 5 per ticket panel. If a question is not answered or doesn't exist, the placeholder resolves to "unknown".
> {style="note"}

**Example usage:**

```text
carry-{ticket.form.2}-runs
```
If form question 2 asks "How many runs?" and the user responds "10", the channel name becomes `carry-10-runs`.

## Interaction User Placeholders

Placeholders for the user who **performed an action** on the ticket (e.g., closed, claimed, or deleted the ticket). These differ from `{user.*}` placeholders, which always refer to the ticket creator.

| Placeholder                         | Description                             | Example Output          |
|-------------------------------------|-----------------------------------------|-------------------------|
| `{interactionUser.id}`              | Discord user ID of the action performer | `987654321098765432`    |
| `{interactionUser.mention}`         | Discord mention format                  | `<@987654321098765432>` |
| `{interactionUser.name}`            | Discord username                        | `StaffMember`           |
| `{interactionUser.displayName}`     | Server-specific nickname or global name | `Support Lead`          |

**Example usage:**

```text
Ticket closed by {interactionUser.mention}
```

## Claimer Placeholders

Placeholders for the support staff member who claimed the ticket. These are only available after a ticket has been claimed.

| Placeholder         | Description                    | Example Output          |
|---------------------|--------------------------------|-------------------------|
| `{claimer.mention}` | Discord mention of the claimer | `<@111222333444555666>` |

> If the ticket is not claimed, this placeholder resolves to an empty string.
> {style="note"}

**Example usage:**

```text
{panel.name}-{ticket.count}-{claimer.mention}
```

## Carry System Placeholders

Placeholders for carry tier and difficulty information. These require the ticket panel to have `relatedCarryTier` (and optionally `relatedCarryDifficulty`) configured.

| Placeholder               | Description                         | Example Output |
|---------------------------|-------------------------------------|----------------|
| `{carry-tier.name}`       | Name of the linked carry tier       | `Floor 7`      |
| `{carry-difficulty.name}` | Name of the linked carry difficulty | `Completion`   |

> If the ticket panel does not have a related carry tier or difficulty configured, these placeholders resolve to empty strings.
> {style="note"}

**Example usage:**

```text
{carry-tier.name}-{carry-difficulty.name}-{ticket.count}
```

Output: `floor-7-completion-42`

## Transcript Placeholders

Placeholders for transcript information. These are primarily used in `userTranscriptDm` messages.

| Placeholder        | Description                         | Example Output                                    |
|--------------------|-------------------------------------|---------------------------------------------------|
| `{transcript.url}` | CDN URL of the generated transcript | `https://cdn.dungeon-hub.net/some-uuid-here.html` |

**Example usage:**

```json
["Your ticket has been closed. View the transcript: {transcript.url}"]
```

## Special Considerations

### Empty or Missing Values

If a placeholder references data that doesn't exist (e.g., `{user.minecraft.name}` for an unlinked user, or `{claimer.mention}` for an unclaimed ticket), the placeholder typically resolves to a fallback string (often "unknown"). This will not cause errors but may result in unexpected replacements (e.g., `support-unlinked-42` if `{user.minecraft.name}` is empty in `support-{user.minecraft.name}-{ticket.count}`).

### Case Sensitivity

Placeholders are **case-sensitive**. Use the exact casing shown in this reference (e.g., `{user.mention}`, not `{User.Mention}`).

### Combining Placeholders

You can combine multiple placeholders in a single template:

```text
{user.minecraft.name}-{carry-tier.name}-ticket-{ticket.count}
```

Output: `technoblade-floor-7-ticket-42`

### Testing Placeholders

The best way to test placeholder behavior is to:
1. Configure a ticket panel in the dashboard
2. Create a test ticket
3. Observe the channel name and welcome message

## Common Use Cases

### Example 1: Personalized Channel Name

```text
{panel.name}-{user.minecraft.name}-{ticket.count}
```
Output: `support-technoblade-42`

### Example 2: Carry Ticket with Difficulty

```text
{carry-tier.name}-{carry-difficulty.name}-{ticket.form.1}runs
```
If form question 1 asks "How many runs?" and the user answers "5":\
Output: `floor-7-completion-5runs`

### Example 3: Welcome Message with Context

```json
{
  "content": "Welcome, {user.mention}!\n\n**Minecraft Account:** {user.minecraft.name}\n**Catacombs Level:** {user.catacombs.level}\n**Ticket ID:** {ticket.id}\n\nA staff member will assist you shortly."
}
```

### Example 4: Transcript DM

```json
[{
  "title": "Support Ticket Closed",
  "description": "Thank you for contacting {panel.name}, {user.globalName}.\n\nView the full conversation here: {transcript.url}",
  "color": 3066993,
  "footer": {
    "text": "Ticket #{ticket.count}"
  }
}]
```

## See Also

- [Ticket System](Ticket-System.md) - Feature overview
- [](Ticket-Panel-Configuration.md) - Where to configure templates that use placeholders
- [](link.topic) - Link Minecraft accounts to enable game-related placeholders
