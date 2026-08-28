# Ticket Panel Configuration

This page provides a comprehensive reference for all ticket panel configuration options available through the [Dungeon Hub Dashboard](https://dungeon-hub.com).

## General Settings

### `name`

**Type:** String (required)\
**Description:** Internal identifier for the ticket panel. Used in channel naming templates via `{panel.name}` and for internal reference.\
**Example:** `"support"`, `"carry-orders"`, `"bug-reports"`

### `displayName`

**Type:** String (optional)\
**Description:** User-visible name displayed on the ticket creation button. If not set, the `name` field is used instead.\
**Example:** `"Support Tickets"`, `"Order a Carry"`

### `emoji`

**Type:** String (optional)\
**Description:** Emoji displayed on the ticket creation button. Supports Unicode emoji (e.g., `"🎫"`) and Discord custom emoji format (e.g., `"<:custom:123456789>"`).\
**Example:** `"🎫"`, `"📝"`, `"<:ticket:987654321>"`

### `requiresLinking`

**Type:** Boolean (default: `false`)\
**Description:** When enabled, users must have a linked Minecraft account (via [](link.topic)) before they can create a ticket. Useful for carry service tickets where verification is required.\
**Example:** `true` for carry tickets, `false` for general support

## Ticket Logic

### `closeable`

**Type:** Boolean (default: `false`)\
**Description:** Determines whether tickets can be closed. When `false`, tickets can only be deleted (permanent removal). When `true`, tickets can be moved to a `Closed` state and potentially reopened later.\
**Example:** `true` for support tickets, `false` for one-time reports

### `closeConfirmation`

**Type:** Boolean (default: `false`)\
**Description:** When enabled, closing a ticket requires confirmation via a button interaction. Prevents accidental closures.\
**Example:** `true` for important tickets, `false` for quick workflows

### `claimable`

**Type:** Boolean (default: `false`)\
**Description:** Enables the ticket-claiming system. Members with `supportRoles` may claim tickets only when `claimable` is `true` and may close tickets only when `closeable` is `true`. Members with `additionalRoles` may also claim tickets when `claimable` is `true`. Members with **Manage Channels** and administrators retain permission to claim and close tickets regardless of these settings. Claiming updates the channel name and permissions. Only the current claimer, a member with **Manage Channels**, or an administrator can unclaim a ticket.\
**Example:** `true` for busy support channels to assign responsibility

### `ticketMessage`

**Type:** JSON String (optional)\
**Description:** The message sent to the ticket channel immediately after creation. Supports Discord message JSON format with `content`, `embeds`, and `buttons` fields. Placeholders are supported (see [Ticket Placeholders](Ticket-Placeholders.md)).\
**Default:**

```json
{
  "content": "Welcome, {user.mention}!\nPlease describe your {panel.name} request below further."
}
```

**Example with embed:**

```json
{
  "content": "Welcome, {user.mention}!",
  "embeds": [{
    "title": "Support Ticket #{ticket.count}",
    "description": "A staff member will assist you shortly.",
    "color": 3447003
  }]
}
```

## Channel Naming Templates

All channel naming templates support placeholders. See [Ticket Placeholders](Ticket-Placeholders.md) for a complete list.

### `openChannelName`

**Type:** String (optional)\
**Description:** Template for naming the channel when a ticket is first created (state: `Open`). If not set, Discord generates a default name.\
**Default:** `"{panel.name}-{ticket.count}"`\
**Example:** `"ticket-{ticket.count}"`, `"{user.name}-support"`, `"carry-{ticket.form.1}"`

### `claimedChannelName`

**Type:** String (optional)\
**Description:** Template for renaming the channel when a support staff member claims the ticket. If not set, the channel name remains unchanged when claimed.\
**Example:** `"{panel.name}-{ticket.count}-claimed"`, `"{claimer.name}-{user.name}"`

### `closedChannelName`

**Type:** String (optional)\
**Description:** Template for renaming the channel when a ticket is closed (state: `Closed`). If not set, the channel name remains unchanged when closed.\
**Example:** `"closed-{panel.name}-{ticket.count}"`, `"archived-{ticket.count}"`

## Transcript Configuration

Transcripts capture the complete message history of a ticket in HTML format and upload it to a CDN.

### `transcriptChannel`

**Type:** Discord Channel ID (optional)\
**Description:** The channel where transcript messages are posted when `closeTranscriptTarget` or `deleteTranscriptTarget` is set to `TranscriptChannel` or `Both`. This is a Discord snowflake ID (64-bit integer).\
**Example:** `1234567890123456789`

> You can also configure this server-wide using the `TRANSCRIPTS_CHANNEL` server property. See [Server Properties](Server-Property.md) for details.
> {style="note"}

### `closeTranscriptTarget`

**Type:** Enum (default: `None`)\
**Description:** Controls where transcripts are sent when a ticket is closed.

**Valid values:**

- `None` - No transcript generated on close
- `User` - Transcript sent via DM to the ticket creator only
- `TranscriptChannel` - Transcript posted in `transcriptChannel` only
- `Both` - Transcript sent to both user DM and transcript channel

### `deleteTranscriptTarget`

**Type:** Enum (default: `None`)\
**Description:** Controls where transcripts are sent when a ticket is deleted.

**Valid values:**

- `None` - No transcript generated on delete
- `User` - Transcript sent via DM to the ticket creator only
- `TranscriptChannel` - Transcript posted in `transcriptChannel` only
- `Both` - Transcript sent to both user DM and transcript channel

### `userTranscriptDm`

**Type:** JSON (optional)\
**Description:** Configuration for the DM message sent to users when a transcript is delivered. This field accepts embeds that will be sent to the user. Supports placeholders including `{transcript.url}`.\
**Default:** `["transcript"]`

**Format options:**

1. **Array of predefined embed types** (strings):
   - `"transcript"` - Uses the built-in transcript embed with ticket information
   
2. **Single embed object** (JSON object with Discord embed fields):

   ```json
   {
     "title": "Ticket Closed",
     "description": "View transcript: {transcript.url}",
     "color": 3447003
   }
   ```
3. **Array of embed objects**:

   ```json
   [
     {
       "title": "Ticket Closed",
       "description": "Your {panel.name} ticket has been closed.",
       "color": 5763719
     },
     {
       "title": "Transcript",
       "description": "View the full conversation: {transcript.url}",
       "color": 3447003
     }
   ]
   ```

**Examples:**

```json
["transcript"]
```

Default behavior - sends a built-in embed with ticket details and transcript link.

```json
[{
  "title": "Support Ticket Closed",
  "description": "Thank you for contacting {panel.name}.\n\nTranscript: {transcript.url}",
  "color": 3066993,
  "footer": {
    "text": "Ticket #{ticket.count}"
  }
}]
```

Custom embed with placeholders.

```json
[
   "transcript",
   {
      "description": "Thank you for trusting into our services! If you enjoyed the services provided by {claimer.mention}, please review them in #reviews.",
      "color": "#A51770",
      "footer": {
         "icon": "https://static.dungeon-hub.net/favicon.gif",
         "text": "discord.dungeon-hub.net"
      }
   }
]
```

Sends the default transcript message, but attaches another custom embed asking the user to review the service.

## Access Control

### `supportRoles`

**Type:** Comma-separated Role IDs (optional)\
**Description:** Discord role IDs for support staff who can access unclaimed tickets. Members with these roles can view, respond to, claim, close, and delete tickets. When `ticket.claimer` is set, permissions for these roles are omitted and the claimer receives the support permissions instead; permissions for `additionalRoles` continue to apply. Each ID is a Discord snowflake (64-bit integer).\
**Format:** Comma-separated list\
**Example:** `1234567890123456789` (single role), `111111111,222222222` (multiple roles)

### `additionalRoles`

**Type:** Comma-separated Role IDs (optional)\
**Description:** Additional Discord role IDs that should have access to tickets. Similar to `supportRoles` but semantically separate for organizational purposes (e.g., moderators vs. administrators). Each ID is a Discord snowflake (64-bit integer).\
**Format:** Comma-separated list

### `openCategories`

**Type:** Comma-separated Category IDs (optional)\
**Description:** Discord category IDs where open tickets should be placed. If multiple categories are provided, the bot selects one (typically the least full category). Each ID is a Discord snowflake (64-bit integer).\
**Format:** Comma-separated list\
**Example:** `1234567890123456789`

### `closedCategories`

**Type:** Comma-separated Category IDs (optional)\
**Description:** Discord category IDs where closed tickets should be moved. If not set, closed tickets remain in their current category. Each ID is a Discord snowflake (64-bit integer).\
**Format:** Comma-separated list

### Permission Structure

Ticket channel permissions are controlled by five permission candidates:

| Candidate           | Description                             | Applied When                                                                                    |
|---------------------|-----------------------------------------|-------------------------------------------------------------------------------------------------|
| **SupportTeam**     | Members with `supportRoles`             | Always applied, except when ticket is claimed (then only TicketClaimer has support permissions) |
| **AdditionalRoles** | Members with `additionalRoles`          | Always applied, including when the ticket is claimed                                            |
| **TicketCreator**   | The user who created the ticket         | Applied when ticket state is `Open`; removed when state is `Closed`                             |
| **TicketClaimer**   | The staff member who claimed the ticket | Applied when ticket is claimed; removed on unclaim                                              |
| **Everyone**        | The `@everyone` role                    | Always applied (VIEW_CHANNEL denied by default)                                                 |

**Permission Flags** (currently hardcoded, not configurable via dashboard):
- `@everyone` role: **Denied** VIEW_CHANNEL (flag `"1024"`)
- All authorized users: **Allowed** VIEW_CHANNEL, SEND_MESSAGES, READ_MESSAGE_HISTORY (flag `"68608"`)

> Permission flags are Discord bitfield values representing specific Discord permissions. These are currently hardcoded in the backend and can only be read via the API, not configured through the dashboard.
> {style="warning"}

**Permission Changes by State:**
- **Creating → Open**: Creator gains access, support roles applied
- **Open → Closed**: Creator **loses access**, support roles maintain access
- **Closed → Open**: Creator regains access
- **Any → Deleted**: Channel deleted (all permissions removed)

## Carry System Integration

### `relatedCarryTier`

**Type:** Carry Tier ID String (optional)\
**Description:** Links the ticket panel to a specific carry tier. When set, the following features are enabled:
- The [](log.topic) command becomes available within tickets, allowing staff to log completed carries directly from the ticket
- Carry tier data is accessible for validation and display
- The tier name can be referenced via `{carry-tier.name}` placeholder

**Example:** `"abc123-tier-id"` (obtained from the dashboard carry tier list)

**Use case:** Enable this for ticket panels dedicated to carry service orders

### `relatedCarryDifficulty`

**Type:** Carry Difficulty ID String (optional, requires `relatedCarryTier`)\
**Description:** Further specifies which difficulty within the carry tier this ticket panel represents. When set:
- Visible carry price embeds are automatically displayed in tickets, showing the current pricing for this specific difficulty
- The difficulty name can be referenced via `{carry-difficulty.name}` placeholder
- Helps organize multiple ticket panels for the same carry tier but different difficulties (e.g., "Normal Carries", "Master Carries")

**Example:** `"def456-difficulty-id"`

**Use case:** Create separate ticket panels for different carry difficulties (e.g., F7 Normal vs. F7 Master)

> **Carry Integration Summary:** Use `relatedCarryTier` alone if you want a general carry panel that enables logging. Use both `relatedCarryTier` and `relatedCarryDifficulty` together for specific carry service tickets that display pricing and collect difficulty-specific information.
> {style="note"}

## Custom Forms

Forms are displayed as Discord modals when users click the ticket creation button. Up to 5 form questions are supported per ticket panel.

### `formQuestions`

**Type:** JSON Array (optional)\
**Description:** Array of form question objects that users must complete before a ticket is created. Each question has a `type` and `data` field.

**Question Types:**

#### 1. Predefined

Built-in form questions provided by the bot.

**Available predefined questions:**
- `ign-display` - Displays the user's linked Minecraft IGN (read-only)
- `carry-difficulty` - Dropdown to select from available difficulties for the linked `relatedCarryTier`
- `carry-amount` - Text input for the number of carries requested

**Example:**

```json
{
  "type": "Predefined",
  "data": "ign-display"
}
```

#### 2. TextInput

Custom text input fields (short text or paragraph).

**Data structure:**

```json
{
  "label": "Question text",
  "placeholder": "Placeholder text...",
  "required": true,
  "style": "<SHORT|PARAGRAPH>"
}
```

**Example:**

```json
{
  "type": "TextInput",
  "data": "{\"label\":\"Issue Description\",\"placeholder\":\"Describe your issue...\",\"required\":true,\"style\":\"PARAGRAPH\"}"
}
```

#### 3. StringSelect

Dropdown selection from predefined options.

**Data structure:**

```json
{
  "label": "Question text",
  "options": ["Option 1", "Option 2", "Option 3"],
  "required": true
}
```

**Example:**

```json
{
  "type": "StringSelect",
  "data": "{\"label\":\"Priority\",\"options\":[\"Low\",\"Medium\",\"High\"],\"required\":true}"
}
```

#### 4. TextDisplay

Information text displayed to the user (no input required).

**Data structure:**

```json
{
  "label": "Title",
  "text": "Information text to display"
}
```

**Example:**

```json
{
  "type": "TextDisplay",
  "data": "{\"label\":\"Important Notice\",\"text\":\"Please ensure you have read the rules before creating a ticket.\"}"
}
```

### Complete Form Example

```json
[
  {
    "type": "Predefined",
    "data": "ign-display"
  },
  {
    "type": "Predefined",
    "data": "carry-difficulty"
  },
  {
    "type": "Predefined",
    "data": "carry-amount"
  },
  {
    "type": "TextInput",
    "data": "{\"label\":\"Additional Notes\",\"placeholder\":\"Any special requests?\",\"required\":false,\"style\":\"PARAGRAPH\"}"
  }
]
```

### Accessing Form Responses

Form responses can be referenced in messages and channel names using placeholders:
- `{ticket.form.1}` - Response to the first form question
- `{ticket.form.2}` - Response to the second form question
- `{ticket.form.3}` through `{ticket.form.5}` - Responses to subsequent questions

## Default Configuration Values

When creating a new ticket panel, the following defaults are applied:

```json
{
  "closeable": false,
  "closeConfirmation": false,
  "claimable": false,
  "requiresLinking": false,
  "openChannelName": "{panel.name}-{ticket.count}",
  "ticketMessage": "{\"content\":\"Welcome, {user.mention}!\\nPlease describe your {panel.name} request below further.\"}",
  "userTranscriptDm": "[\"transcript\"]",
  "closeTranscriptTarget": "None",
  "deleteTranscriptTarget": "None",
  "permissions": {
    "SupportTeam": { "Allowed": "68608" },
    "AdditionalRoles": { "Allowed": "68608" },
    "TicketCreator": { "Allowed": "68608" },
    "TicketClaimer": { "Allowed": "68608" },
    "Everyone": { "Denied": "1024" }
  }
}
```

## Validation & Constraints

- **JSON Fields**: `ticketMessage`, `userTranscriptDm`, and `formQuestions` must be valid JSON. The dashboard validates JSON syntax before saving.
- **ID Fields**: Role IDs, category IDs, and channel IDs must be valid Discord snowflake IDs (64-bit integers).
- **Form Limits**: Maximum 5 form questions per ticket panel.
- **Enum Values**: `closeTranscriptTarget` and `deleteTranscriptTarget` must be one of: `None`, `User`, `TranscriptChannel`, `Both`.

## See Also

- [Ticket System](Ticket-System.md) - Feature overview and ticket lifecycle
- [Ticket Placeholders](Ticket-Placeholders.md) - Complete placeholder reference for templates
- [Server Properties](Server-Property.md) - Configure server-wide settings like `TRANSCRIPTS_CHANNEL`
